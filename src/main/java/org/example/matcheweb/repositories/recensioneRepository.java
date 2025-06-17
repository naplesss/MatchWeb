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
        String sql = "INSERT INTO RECENSIONI VALUES (DEFAULT,?, ?, ?)";
        jdbc.update(sql,
                recensione.getUSER_ID(),
                recensione.getVOTO(),







                recensione.getCOMMENTO()
                );
    }

    public List<Recensione> findAllRecensioni(){
        String sql = "SELECT * FROM RECENSIONI ";
        RowMapper<Recensione> RecensioneRowMapper = (r,i)->{
            Recensione RowObject = new Recensione();
            RowObject.setID(r.getInt("ID"));

            RowObject.setUSER_ID(r.getInt("USER_ID"));
            RowObject.setVOTO(r.getInt("VOTO"));
            RowObject.setCOMMENTO(r.getString("COMMENTO"));
            return RowObject;
        };
        return jdbc.query(sql,RecensioneRowMapper);
    }
}