package br.com.projetozeta.ws.service;

import br.com.projetozeta.ws.repository.TagRepository;
import br.com.projetozeta.ws.domain.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dekkz on 16/05/17.
 */

@Service
public class TagService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagRepository repository;


    public List<Tag> findAll(){
        logger.info("TagService - Started method findAll");
        return repository.findAll();
    }

    public List<Tag> findTagsByPlace(long id){
        logger.info("TagService - Started method findTagsByPlace");
        return repository.findTagsByPlace(id);
    }

    public List<Tag> findTagsByUser(long id){
        logger.info("TagService - Started method findTagsByUser");
        return repository.findTagsByUser(id);
    }

    public Tag findById(long id){
        logger.info("TagService - Started method findById");
        return repository.findById(id);
    }

    public Tag findByTag(String tag){
        logger.info("TagService - Started method findByTag");
        return repository.findByTag(tag);
    }

    public Tag save(Tag tag){
        logger.info("TagService - Started method save");

        Tag findTag = repository.findByTag(tag.getTag());
        if(findTag != null){
            return findTag;
        }

        return repository.save(tag);
    }

    public void delete(long id){
        logger.info("TagService - Started method delete");
        repository.delete(id);
    }

    public Tag update(Tag tag){
        logger.info("TagService - Started method update");
        return repository.update(tag);

    }

}
