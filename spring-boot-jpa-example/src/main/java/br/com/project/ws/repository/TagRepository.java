package br.com.project.ws.repository;

import br.com.project.ws.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dekkz on 07/05/17.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    public List<Tag> findAll();
    public Tag findByTag(final String tag);
}
