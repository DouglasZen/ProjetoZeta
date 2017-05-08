package br.com.project.ws.service;

import br.com.project.ws.domain.Place;
import br.com.project.ws.domain.Tag;
import br.com.project.ws.repository.PlaceRepository;
import br.com.project.ws.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dekkz on 07/05/17.
 */

@Service
public class PlaceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlaceRepository repository;

    @Autowired
    private TagRepository tagRepository;

    public List<Place> findAll(){
        logger.info("PlaceRepository - Started method findAll");
        return repository.findAll();
    }

    public Place findById(long id){
        logger.info("PlaceRepository - Started method findById");
        return repository.findOne(id);
    }

    public Place save(Place place){
        logger.info("PlaceRepository - Started method save");

        // check if exists places and tags in database
        Place findPlace = repository.findByNome(place.getNome());
        if(findPlace != null){

            for (Tag t : place.getTags()){
                Tag findTag = tagRepository.findByTag(t.getTag());

                if (findTag != null){
                    findPlace.getTags().add(findTag);
                }else{
                    findPlace.getTags().add(t);
                }
            }
            return repository.save(findPlace);
        }

        for (Tag t : place.getTags()){
            Tag findTag = tagRepository.findByTag(t.getTag());
            Set<Tag> tags= new HashSet<Tag>();
            tags.add(findTag);

            if (findTag != null){
                place.setTags(tags);
            }
        }



        return repository.save(place);
    }

    public void delete(long id){
        logger.info("PlaceRepository - Started method delete");
        repository.delete(id);
    }

    public Place update(long id, Place place){
        logger.info("PlaceRepository - Started method update");
        Place placeUpdate = findById(id);

        if(placeUpdate == null){
            logger.error("Attempted to update a Place Entity does not exists");
            logger.info("< update {}", place.getId());
        }

        placeUpdate.setNome(place.getNome());

        return repository.save(placeUpdate);
    }
}
