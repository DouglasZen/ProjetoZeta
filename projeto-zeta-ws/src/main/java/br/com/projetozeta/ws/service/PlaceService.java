package br.com.projetozeta.ws.service;

import br.com.projetozeta.ws.repository.PlaceRepository;
import br.com.projetozeta.ws.domain.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dekkz on 16/05/17.
 */

@Service
public class PlaceService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlaceRepository repository;



    public List<Place> findAll(){
        logger.info("PlaceRepository - Started method findAll");
        return repository.findAll();
    }

    public List<Place> findPlacesByTag(long id){
        logger.info("PlaceRepository - Started method findPlacesByTag");
        return repository.findPlacesByTag(id);
    }

    public List<Place> findPlacesByUser(long id){
        logger.info("PlaceRepository - Started method findPlacesByUser");
        return repository.findPlacesByUser(id);
    }

    public Place findById(long id){
        logger.info("PlaceRepository - Started method findById");
        return repository.findById(id);
    }

    public Place findByNome(String nome){
        logger.info("PlaceRepository - Started method findByNome");
        return repository.findByNome(nome);
    }

    public Place save(Place place){
        logger.info("PlaceRepository - Started method save");

        Place findPlace = repository.findByNome(place.getNome());
        if(findPlace != null){
            return findPlace;
        }

        return repository.save(place);
    }

    public void delete(long id){
        logger.info("PlaceRepository - Started method delete");
        repository.delete(id);
    }

    public Place update(Place place){
        logger.info("PlaceRepository - Started method update");
        return repository.update(place);
    }

}
