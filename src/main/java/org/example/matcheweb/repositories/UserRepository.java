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

            public List<User> findAllUsers () {
                String sql = "SELECT * FROM USERDATA JOIN AUTHORITIES ON USERDATA.ID=AUTHORITIES.ID ORDER BY Username";

                RowMapper<User> userRowMapper = (r, i) -> {
                    User rowObject = new User();
                    rowObject.setUsername(r.getString("USERNAME"));
                    rowObject.setNome(r.getString("FIRSTNAME"));
                    rowObject.setCognome(r.getString("LASTNAME"));
                    rowObject.setEmail(r.getString("EMAIL"));
                    rowObject.setRole(r.getString("AUTHORITY"));
                    return rowObject;
                };
                return jdbc.query(sql, userRowMapper);
            }

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
            public List<User> getClassificaUtenti (){
            String sql = "SELECT username,SUM (punti) AS points FROM (SELECT username,punti FROM PUNTI JOIN USERDATA ON PUNTI.ID=USERDATA.ID ORDER BY punti DESC) AS subquery GROUP BY username";
            RowMapper<User> rankingRowMapper = (r,i)->{
                User user = new User();
                user.setUsername(r.getString("usernsme"));
                user.setPunti(r.getInt("tot_punti"));
                    user.setClassifica(i + 1);
                    return user;
                };
            return jdbc.query(sql,rankingRowMapper);
            }

            public boolean isMaggiorenne(Date birthdate){
               int compleanno = birthdate.toLocalDate().getYear();
                int annoCorrente = LocalDate.now().getYear();
                return annoCorrente-compleanno <= 18;

            }


        public User findByUsername(String username) {
            String sql = "SELECT * FROM USERDATA WHERE USERNAME = ?";
            RowMapper<User> userRowMapper = (r,i)->{
                User user = new User();
                user.setId(r.getInt("id"));
                user.setUsername(r.getString("username"));
                user.setNome(r.getString("nome"));
                user.setCognome(r.getString("cognome"));
                user.setEmail(r.getString("email"));
                user.setPunti(r.getInt("punti"));
                user.setClassifica(r.getInt("classifica"));
                return user;
            };
            return jdbc.queryForObject(sql,userRowMapper,username);
        }
    }
