package br.com.projetozeta.ws.service;

import br.com.projetozeta.ws.domain.User;
import br.com.projetozeta.ws.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dekkz on 16/05/17.
 */

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository repository;


    public List<User> findAll(){
        logger.info("UserService - Started method findAll");
        return repository.findAll();
    }

    public List<User> findUsersByPlace(long id){
        logger.info("UserService - Started method findUsersByPlace");
        return repository.findUsersByPlace(id);
    }

    public List<User> findUsersByTag(long id){
        logger.info("UserService - Started method findUsersByTag");
        return repository.findUsersByTag(id);
    }

    public User findById(long id){
        logger.info("UserService - Started method findById");
        return repository.findById(id);
    }

    public User findByNome(String nome){
        logger.info("UserService - Started method findByNome");
        return repository.findByNome(nome);
    }

    public User save(User user){
        logger.info("UserService - Started method save");

        User findUser = repository.findByNome(user.getNome());
        if(findUser != null){
            return findUser;
        }
        return repository.save(user);
    }

    public void delete(long id){
        logger.info("UserService - Started method delete");
        repository.delete(id);
    }

    public User update(User user){
        logger.info("UserService - Started method update");
        return repository.update(user);

    }
}
