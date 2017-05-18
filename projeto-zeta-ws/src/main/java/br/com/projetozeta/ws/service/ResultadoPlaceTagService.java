package br.com.projetozeta.ws.service;

import br.com.projetozeta.ws.domain.ResultadoPlaceTag;
import br.com.projetozeta.ws.domain.User;
import br.com.projetozeta.ws.repository.PlaceRepository;
import br.com.projetozeta.ws.repository.ResultadoPlaceTagRepository;
import br.com.projetozeta.ws.repository.TagRepository;
import br.com.projetozeta.ws.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dekkz on 17/05/17.
 */

@Service
public class ResultadoPlaceTagService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResultadoPlaceTagRepository resultadoPlaceTagRepository;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    public void save(ResultadoPlaceTag resultadoPlaceTag){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(new Date());

        resultadoPlaceTag.setPlace(placeService.save(resultadoPlaceTag.getPlace()));
        resultadoPlaceTag.setTag(tagService.save(resultadoPlaceTag.getTag()));
        resultadoPlaceTag.setUser(userService.save(resultadoPlaceTag.getUser()));

        resultadoPlaceTag.setDate(new Date());

        resultadoPlaceTagRepository.save(resultadoPlaceTag);
    }

}
