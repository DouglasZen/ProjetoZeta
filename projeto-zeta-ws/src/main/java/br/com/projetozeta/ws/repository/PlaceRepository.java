package br.com.projetozeta.ws.repository;

import br.com.projetozeta.ws.domain.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by dekkz on 17/05/17.
 */

@Repository
public class PlaceRepository {

    private final static String INSERT_PLACE = "INSERT INTO place(nome) VALUES(?)";
    private final static String UPDATE_PLACE = "UPDATE place SET nome = ? WHERE id = ?";
    private final static String DELETE_PLACE = "DELETE FROM place WHERE id = ?";
    private final static String FIND_ALL_PLACES = "SELECT * FROM place";
    private final static String FIND_BY_ID_PLACE = "SELECT * FROM place WHERE id = ?";
    private final static String FIND_BY_NOME = "SELECT * FROM place WHERE nome = ?";

    private final static String FIND_PLACES_BY_TAG = "SELECT p.id, p.nome FROM place_tag pt "
            + "INNER JOIN place p ON pt.place_id = p.id "
            + "WHERE pt.tag_id = ?";

    private final static String FIND_PLACES_BY_USER = "SELECT p.id, p.nome FROM place_tag pt "
            + "INNER JOIN place p ON pt.place_id = p.id "
            + "WHERE pt.user_id = ?";


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Place> placeRowMapper = (rs, rowNum) -> new Place(
            rs.getLong("id"),
            rs.getString("nome")
    );

    public List<Place> findAll(){
        try{
            List<Place> places = jdbcTemplate.query(FIND_ALL_PLACES, placeRowMapper);
            return places;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public List<Place> findPlacesByTag(long id){
        try{
            List<Place> places = jdbcTemplate.query(FIND_PLACES_BY_TAG, new Long[]{id}, placeRowMapper);
            return places;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Place> findPlacesByUser(long id){
        try{
            List<Place> places = jdbcTemplate.query(FIND_PLACES_BY_USER, new Long[]{id}, placeRowMapper);
            return places;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Place findById(long id){
        try{
            Place place = jdbcTemplate.queryForObject(FIND_BY_ID_PLACE, new Long[]{id}, placeRowMapper);
            return place;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Place findByNome(String nome){
        try{
            Place place = new Place();
            place.setNome(nome);
            place = jdbcTemplate.queryForObject(FIND_BY_NOME, new String[]{place.getNome()}, placeRowMapper);
            return place;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Place save(Place place){
        //jdbcTemplate.update(INSERT_PLACE, place.getNome());

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(INSERT_PLACE, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, place.getNome());
                return statement;
            }

        }, holder);

        long primaryKey = holder.getKey().longValue();
        place.setId(primaryKey);

        return place;
    }


    public Place update(Place place){
        jdbcTemplate.update(UPDATE_PLACE, place.getId(), place.getNome());
        return place;

    }

    public void delete(long id){
        jdbcTemplate.update(DELETE_PLACE, id);
    }


}
