package org.example.matcheweb.repositories;

import org.example.matcheweb.pojos.SecurityUser;
import org.example.matcheweb.pojos.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
            String sql = "SELECT username,SUM (points) AS points FROM (SELECT username,points FROM POINTS JOIN USERDATA ON POINTS.ID=USERDATA.ID ORDER BY points DESC) AS subquery GROUP BY username";
            RowMapper<User> rankingRowMapper = (r,i)->{
                User user = new User();
                user.setUsername(r.getString("usernsme"));
                user.setPunti(r.getInt("tot_punti"));
                    user.setClassifica(i + 1);
                    return user;
                };
            return jdbc.query(sql,rankingRowMapper);
            }

    }
