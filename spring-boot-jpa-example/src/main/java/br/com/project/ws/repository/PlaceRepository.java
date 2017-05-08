package br.com.project.ws.repository;

import br.com.project.ws.domain.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dekkz on 07/05/17.
 */
public interface PlaceRepository extends CrudRepository<Place, Long> {
    public List<Place> findAll();
    public Place findByNome(final String nome);
}
