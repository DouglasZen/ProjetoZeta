package br.com.project.ws.service;

import br.com.project.ws.domain.Tag;
import br.com.project.ws.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dekkz on 07/05/17.
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

    public Tag findById(long id){
        logger.info("TagService - Started method findById");
        return repository.findOne(id);
    }

    public Tag save(Tag tag){
        logger.info("TagService - Started method save");
        return repository.save(tag);
    }

    public void delete(long id){
        logger.info("TagService - Started method delete");
        repository.delete(id);
    }

    public Tag update(long id, Tag tag){
        logger.info("TagService - Started method update");
        Tag tagUpdate = findById(id);

        if(tagUpdate == null){
            logger.error("Attempted to update a Empresa Entity does not exists");
            logger.info("< update {}", tag.getId());
        }

        tagUpdate.setTag(tag.getTag());

        return repository.save(tagUpdate);
    }
}
