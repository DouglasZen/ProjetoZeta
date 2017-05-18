package br.com.projetozeta.ws.repository;

import br.com.projetozeta.ws.domain.Place;
import br.com.projetozeta.ws.domain.ResultadoPlaceTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by dekkz on 17/05/17.
 */

@Repository
public class ResultadoPlaceTagRepository {

    private final static String INSERT_PLACE_TAG = "INSERT INTO place_tag(place_id, tag_id, user_id, date) VALUES(?,?,?,?)";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(ResultadoPlaceTag resultadoPlaceTag){
        jdbcTemplate.update(INSERT_PLACE_TAG,
                resultadoPlaceTag.getPlace().getId(),
                resultadoPlaceTag.getTag().getId(),
                resultadoPlaceTag.getUser().getId(),
                resultadoPlaceTag.getDate()

        );
    }

}
