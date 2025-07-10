package org.example.matcheweb.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class PartiteRepository {
    private final JdbcTemplate jdbc;

    public PartiteRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void NuovaSchedina(int id,int punti){
        String sql = "INSERT INTO GIORNATE VALUES (?, ?, ?)";
        jdbc.update(sql,id, LocalDate.now().toString(),punti);
    }

    public boolean giaGiocato(int id){
        String sql = "SELECT count(*) FROM GIORNATE WHERE ID = ? AND giorno=?";
        return jdbc.queryForObject(sql,Integer.class,id,LocalDate.now().toString())>0;
    }
}
