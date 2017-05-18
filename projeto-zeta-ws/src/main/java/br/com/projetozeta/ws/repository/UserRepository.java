package br.com.projetozeta.ws.repository;


import br.com.projetozeta.ws.domain.Place;
import br.com.projetozeta.ws.domain.User;
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
public class UserRepository {
    private final static String INSERT_USER = "INSERT INTO user(nome, email, id_provider) VALUES(?, ?, ?)";
    private final static String UPDATE_USER = "UPDATE user SET nome = ?, email = ?, id_provider = ? WHERE id = ?";
    private final static String DELETE_USER = "DELETE FROM user WHERE id = ?";
    private final static String FIND_USERS = "SELECT * FROM user";

    private final static String FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private final static String FIND_USER_BY_NOME = "SELECT * FROM user WHERE nome = ?";

    private final static String FIND_USERS_BY_PLACE = "SELECT u.id, u.nome, u.email, u.id_provider FROM place_tag pt "
            + "INNER JOIN user u ON pt.user_id = u.id "
            + "WHERE pt.place_id = ?";

    private final static String FIND_USERS_BY_TAG = "SELECT u.id, u.nome, u.email, u.id_provider FROM place_tag pt "
            + "INNER JOIN user u ON pt.user_id = u.id "
            + "WHERE pt.tag_id = ?";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getLong("id"),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("id_provider")
    );

    public List<User> findAll(){
        try{
            List<User> users = jdbcTemplate.query(FIND_USERS, userRowMapper);
            return users;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public List<User> findUsersByPlace(long id){
        try{
            List<User> users = jdbcTemplate.query(FIND_USERS_BY_PLACE, new Long[]{id}, userRowMapper);
            return users;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public List<User> findUsersByTag(long id){
        try{
            List<User> users = jdbcTemplate.query(FIND_USERS_BY_TAG, new Long[]{id}, userRowMapper);
            return users;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findById(long id){
        try{
            User user = jdbcTemplate.queryForObject(FIND_USER_BY_ID, new Long[]{id}, userRowMapper);
            return user;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public User findByNome(String nome){
        try{
            User user = new User();
            user.setNome(nome);
            user = jdbcTemplate.queryForObject(FIND_USER_BY_NOME, new String[]{user.getNome()}, userRowMapper);
            return user;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public User save(User user){
        //jdbcTemplate.update(INSERT_USER, user.getNome(), user.getEmail(), user.getIdProvider());

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getNome());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getIdProvider());
                return statement;
            }

        }, holder);

        long primaryKey = holder.getKey().longValue();
        user.setId(primaryKey);

        return user;
    }


    public User update(User user){
        jdbcTemplate.update(UPDATE_USER, user.getId(), user.getNome(), user.getEmail(), user.getIdProvider());
        return user;

    }

    public void delete(long id){
        jdbcTemplate.update(DELETE_USER, id);
    }

}
