package org.example.matcheweb.repositories;

import org.example.matcheweb.pojos.SecurityUser;
import org.example.matcheweb.pojos.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


    @Repository
    public class UserRepository {
        private final JdbcTemplate jdbc;
        private final UserDetailsManager userDetailsManager;
        private final PasswordEncoder passwordEncoder;

        public UserRepository(JdbcTemplate jdbc,
                              UserDetailsManager userDetailsManager,
                              PasswordEncoder passwordEncoder) {
            this.jdbc = jdbc;
            this.userDetailsManager = userDetailsManager;
            this.passwordEncoder = passwordEncoder;
        }
            public boolean userExists (String username){
                return userDetailsManager.userExists(username);
            }
            //restituisce una lista di utenti ordinati alfabeticamente
            public List<User> findAllUsers () {
                String sql = "SELECT * FROM USERDATA JOIN AUTHORITIES ON USERDATA.ID=AUTHORITIES.ID ORDER BY Username";

                RowMapper<User> userRowMapper = (r, i) -> {
                    User rowObject = new User();
                    rowObject.setUsername(r.getString("USERNAME"));
                    rowObject.setNome(r.getString("NOME"));
                    rowObject.setCognome(r.getString("COGNOME"));
                    rowObject.setEmail(r.getString("EMAIL"));
                    rowObject.setRole(r.getString("AUTHORITY"));
                    return rowObject;
                };
                return jdbc.query(sql, userRowMapper);
            }
            //aggiunge un utente al database
            public void addUser (User user){
                String sql = "INSERT INTO USERDATA VALUES (DEFAULT, ?, ?, ?, ?,?, ?, ?)";
                jdbc.update(sql,
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getBirthdate(),
                        user.getsport(),
                        user.getsquadra()
                );
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userDetailsManager.createUser(new SecurityUser(user));
            }
            //restituisce la classifica degli utenti
            public List<User> getClassificaUtenti (){
            String sql = "SELECT username,SUM (punti) AS tot_punti FROM (SELECT username, GIORNATE.punti FROM GIORNATE JOIN USERDATA ON GIORNATE.ID=USERDATA.ID ORDER BY punti DESC) AS subquery GROUP BY username";
            RowMapper<User> rankingRowMapper = (r,i)->{
                User user = new User();
                user.setUsername(r.getString("username"));
                user.setPunti(r.getInt("tot_punti"));
                    user.setRank(i + 1);
                    return user;
                };
            return jdbc.query(sql,rankingRowMapper);
            }
            //per controllare che l'utente sia maggiorenne
            public boolean isMaggiorenne(Date birthdate){
               int compleanno = birthdate.toLocalDate().getYear();
                int annoCorrente = LocalDate.now().getYear();
                return annoCorrente-compleanno <= 18;

            }

        //trova un utente dal suo username
        public User findByUsername(String username) {
            String sql = "SELECT * FROM USERDATA WHERE USERNAME = ?";
            RowMapper<User> userRowMapper = (r,i)->{
                User user = new User();
                user.setId(r.getInt("id"));
                user.setUsername(r.getString("username"));
                user.setNome(r.getString("nome"));
                user.setCognome(r.getString("cognome"));
                user.setEmail(r.getString("email"));
                return user;
            };

            return jdbc.queryForObject(sql,userRowMapper,username);
        }
        public User findByUsernameXProfilo(String username) {
            System.out.println(username);
            String sql = "SELECT * FROM USERDATA" +
                    " JOIN AUTHORITIES ON USERDATA.ID=AUTHORITIES.ID " +
                   /* "LEFT OUTER JOIN GIORNATE ON USERDATA.ID=GIORNATE.ID */"WHERE USERDATA.USERNAME = ?";
            RowMapper<User> userRowMapper = (r,i)->{
                User user = new User();
                user.setId(r.getInt("id"));
                user.setUsername(r.getString("username"));
                user.setNome(r.getString("nome"));
                user.setCognome(r.getString("cognome"));
                user.setBirthdate(r.getDate("birthdate"));
                user.setRole(r.getString("AUTHORITY"));
                user.setEmail(r.getString("email"));
                return user;
            };

            User user = jdbc.queryForObject(sql,userRowMapper,username);
            sql =   "SELECT COUNT(*) AS n_giornate FROM giornate WHERE ID = ?";
            RowMapper<Integer> intRowMapper = (resultSet, rowNum) -> resultSet.getInt("n_giornate");
            user.setNumschedine(jdbc.queryForObject(sql, intRowMapper, user.getId()));

            sql = "SELECT punti AS tot_punti FROM giornate WHERE ID = ? AND giorno=?";
            intRowMapper = (resultSet, rowNum) -> resultSet.getInt("tot_punti");
            user.setPunti(jdbc.queryForObject(sql, intRowMapper, user.getId(), LocalDate.now().toString()));

            sql = "SELECT count(*) as rank from giornate where punti>?";
            intRowMapper = (resultSet, rowNum) -> resultSet.getInt("rank");
            user.setRank(jdbc.queryForObject(sql, intRowMapper, user.getPunti())+1);

            sql = "SELECT premio FROM premi WHERE username = ?";
            RowMapper<String> premioRowMapper = (resultSet, rowNum) -> resultSet.getString("premio");
            user.setPremi(jdbc.query(sql, premioRowMapper, user.getUsername()));

            return user;

        }
        //cambia la password di un utente
        public boolean cambiaPassword(String username, String nuova, String vecchia) {
            String sql = "SELECT password FROM users WHERE username = ?";
            RowMapper<String> passwordRowMapper = (r, i) -> r.getString("password");
            String attuale = jdbc.queryForObject(sql, passwordRowMapper, username);
            boolean result = passwordEncoder.matches(vecchia, attuale);
            if (result) {
                userDetailsManager.changePassword(passwordEncoder.encode(vecchia), passwordEncoder.encode(nuova));
            }
            return result;
        }
        //upgrade user
        public void upgradeUser(String username){
            String sql = "UPDATE AUTHORITIES SET authority = 'ROLE_MODERATOR' WHERE USERNAME = ?";
            jdbc.update(sql, username);
        }
        //restituisce lo sport
        public String FindSport(String username){
            String sql = "SELECT SPORT FROM USERDATA WHERE USERNAME = ?";
            RowMapper<String> FindSport = (r,i)->{
                String sport = r.getString("sport");
                return sport;
            };
            return jdbc.queryForObject(sql,FindSport,username);

        }

    }
