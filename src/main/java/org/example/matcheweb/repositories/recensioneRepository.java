package org.example.matcheweb.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class recensioneRepository {
    private final JdbcTemplate jdbc;

    public recensioneRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void addRecensione(int userId, int voto, String commento) {
        String sql = "INSERT INTO RECENSIONI (USER_ID, voto, commento) VALUES (?, ?, ?)";
        jdbc.update(sql, userId, voto, commento);
    }
}