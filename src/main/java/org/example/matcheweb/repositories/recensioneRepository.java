package org.example.matcheweb.repositories;

import org.example.matcheweb.pojos.Recensione;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class recensioneRepository {
    private final JdbcTemplate jdbc;

    public recensioneRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void addRecensione(Recensione recensione) {
        String sql = "INSERT INTO RECENSIONI (USER_ID, voto, commento) VALUES (?, ?, ?)";
        jdbc.update(sql, recensione.getUserId(),recensione.getVoto(),recensione.getCommento());
    }
    public List<Recensione> findAllRecensioni(){
        String sql = "SELECT * FROM RECENSIONI ";
        RowMapper<Recensione> RecensioneRowMapper = (r,i)->{
            Recensione RowObject = new Recensione();
            RowObject.setId(r.getInt("id"));

            RowObject.setUserId(r.getInt("user_id"));
            RowObject.setVoto(r.getInt("voto"));
            RowObject.setCommento(r.getString("commento"));
            return RowObject;
        };
        return jdbc.query(sql,RecensioneRowMapper);
    }
}