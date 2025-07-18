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
    //aggiunge una reccensione al database
    public void addRecensione(Recensione recensione) {
        String sql = "INSERT INTO RECENSIONI VALUES (DEFAULT,?, ?, ?)";
        jdbc.update(sql,
                recensione.getUSER_ID(),
                recensione.getVOTO(),
                recensione.getCOMMENTO()
                );
    }
    //trova tutte le recensioni nel database
    public List<Recensione> findAllRecensioni(){
        String sql = "SELECT * FROM RECENSIONI ";
        RowMapper<Recensione> RecensioneRowMapper = (r,i)->{
            Recensione RowObject = new Recensione();
            RowObject.setID(r.getInt("id"));

            RowObject.setUSER_ID(r.getInt("userId"));
            RowObject.setVOTO(r.getInt("voto"));
            RowObject.setCOMMENTO(r.getString("commento"));
            return RowObject;
        };
        return jdbc.query(sql,RecensioneRowMapper);
    }
}