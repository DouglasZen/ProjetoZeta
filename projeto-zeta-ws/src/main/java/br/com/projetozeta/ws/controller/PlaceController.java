package br.com.projetozeta.ws.controller;

import br.com.projetozeta.ws.domain.Place;
import br.com.projetozeta.ws.service.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by dekkz on 16/05/17.
 */


@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlaceService placeService;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Place>> findAll() {
        logger.info("PlaceController - Started method findAll");
        Collection<Place> places = placeService.findAll();
        return new ResponseEntity<Collection<Place>>(places, HttpStatus.OK);
    }

    @RequestMapping(value = "/tags/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Place>> findPlacesByTag(@PathVariable long id) {
        logger.info("PlaceController - Started method findPlacesByTag");
        Collection<Place> places = placeService.findPlacesByTag(id);
        return new ResponseEntity<Collection<Place>>(places, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Place>> findPlacesByUser(@PathVariable long id) {
        logger.info("PlaceController - Started method findPlacesByUser");
        Collection<Place> places = placeService.findPlacesByUser(id);
        return new ResponseEntity<Collection<Place>>(places, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Place> findById(@PathVariable long id) {
        logger.info("PlaceController - Started method findById");
        Place place = placeService.findById(id);
        if (place == null)
            return new ResponseEntity<Place>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Place>(place, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Place> save(@RequestBody Place place) {
        logger.info("PlaceController - Started method save");
        Place savePlace = placeService.save(place);
        return new ResponseEntity<Place>(savePlace, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Place> delete(@PathVariable long id) {
        logger.info("PlaceController - Started method delete");
        placeService.delete(id);
        return new ResponseEntity<Place>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Place> update(@PathVariable long id, @RequestBody Place place) {
        logger.info("PlaceController - Started method update with id: " + id);
        Place placeUpdate = placeService.update(place);
        return new ResponseEntity<Place>(placeUpdate, HttpStatus.OK);
    }
}