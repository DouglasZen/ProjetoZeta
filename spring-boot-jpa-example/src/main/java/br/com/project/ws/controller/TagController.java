package br.com.project.ws.controller;

import br.com.project.ws.domain.Tag;
import br.com.project.ws.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by dekkz on 07/05/17.
 */

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Tag>> findAll(){
        logger.info("TagController - Started method findAll");
        Collection<Tag> tags =  tagService.findAll();
        return new ResponseEntity<Collection<Tag>>(tags, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tag> findById(@PathVariable int id){
        logger.info("TagController - Started method findById");
        Tag tag = tagService.findById(id);
        if(tag == null)
            return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Tag> save(@RequestBody Tag tag){
        logger.info("TagController - Started method save");
        Tag saveTag = tagService.save(tag);
        return new ResponseEntity<Tag>(saveTag, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Tag> delete(@PathVariable int id){
        logger.info("TagController - Started method delete");
        tagService.delete(id);
        return new ResponseEntity<Tag>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Tag> update(@PathVariable int id, @RequestBody Tag tag){
        logger.info("TagController - Started method update with id: " + id);
        Tag tagUpdate = tagService.update(id, tag);
        return new ResponseEntity<Tag>(tagUpdate, HttpStatus.OK);
    }

}
