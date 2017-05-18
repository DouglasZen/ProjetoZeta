package br.com.projetozeta.ws.repository;

import br.com.projetozeta.ws.domain.Place;
import br.com.projetozeta.ws.domain.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
public class TagRepository {

    private final static String INSERT_TAG = "INSERT INTO tag(tag) VALUES(?)";
    private final static String UPDATE_TAG = "UPDATE tag SET tag = ? WHERE id = ?";
    private final static String DELETE_TAG = "DELETE FROM tag WHERE id = ?";
    private final static String FIND_ALL_TAGS = "SELECT * FROM tag";
    private final static String FIND_BY_ID_TAG = "SELECT * FROM tag WHERE id = ?";
    private final static String FIND_BY_TAG = "SELECT * FROM tag WHERE tag = ?";

    private final static String FIND_TAGS_BY_PLACE = "SELECT t.id, t.tag FROM place_tag pt "
            + "INNER JOIN tag t ON pt.tag_id = t.id "
            + "WHERE pt.place_id = ?";

    private final static String FIND_TAGS_BY_USER = "SELECT t.id, t.tag FROM place_tag pt "
            + "INNER JOIN tag t ON pt.tag_id = t.id "
            + "WHERE pt.user_id = ?";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Tag> tagRowMapper = (rs, rowNum) -> new Tag(
            rs.getLong("id"),
            rs.getString("tag")
    );

    public List<Tag> findAll(){
        try{
            List<Tag> tags = jdbcTemplate.query(FIND_ALL_TAGS, tagRowMapper);
            return tags;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Tag> findTagsByPlace(long id){
        try{
            List<Tag> tags = jdbcTemplate.query(FIND_TAGS_BY_PLACE, new Long[]{id},  tagRowMapper);
            return tags;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public List<Tag> findTagsByUser(long id){
        try{
            List<Tag> tags = jdbcTemplate.query(FIND_TAGS_BY_USER, new Long[]{id}, tagRowMapper);
            return tags;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public Tag findById(long id){
        try{
            Tag tag = jdbcTemplate.queryForObject(FIND_BY_ID_TAG, new Long[]{id}, tagRowMapper);
            return tag;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public Tag findByTag(String nome){
        try{
            Tag tag = new Tag();
            tag.setTag(nome);
            tag = jdbcTemplate.queryForObject(FIND_BY_TAG, new String[]{tag.getTag()}, tagRowMapper);
            return tag;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public Tag save(Tag tag){

        //jdbcTemplate.update(INSERT_TAG, tag.getTag());

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(INSERT_TAG, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, tag.getTag());
                return statement;
            }

        }, holder);

        long primaryKey = holder.getKey().longValue();
        tag.setId(primaryKey);

        return tag;
    }


    public Tag update(Tag tag){
        jdbcTemplate.update(UPDATE_TAG   , tag.getId(), tag.getTag());
        return tag;

    }

    public void delete(long id){
        jdbcTemplate.update(DELETE_TAG, id);
    }

}
