package br.com.projetozeta.ws.controller;

import br.com.projetozeta.ws.domain.ResultadoPlaceTag;
import br.com.projetozeta.ws.service.ResultadoPlaceTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dekkz on 17/05/17.
 */

@RestController
@RequestMapping("/api/placetags")
public class ResultadoPlaceTagController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ResultadoPlaceTagService resultadoPlaceTagService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResultadoPlaceTag> save(@RequestBody ResultadoPlaceTag resultadoPlaceTag){
        logger.info("ResultadoPlaceTagController - Started method save");
        resultadoPlaceTagService.save(resultadoPlaceTag);
        return new ResponseEntity<ResultadoPlaceTag>(resultadoPlaceTag, HttpStatus.CREATED);
    }



}
