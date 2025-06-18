package org.example.matcheweb.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoriesPremi {
    private final JdbcTemplate jdbc;

    public RepositoriesPremi(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    //aggiunge un premio ad un user
    public void addPremi(String username,String premio) {
        String sql = "INSERT INTO PREMI VALUES (DEFAULT,?,?)";
        jdbc.update(sql, username,premio);
    }
}
