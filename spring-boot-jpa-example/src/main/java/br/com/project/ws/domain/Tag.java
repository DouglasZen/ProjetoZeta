package br.com.project.ws.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dekkz on 06/05/17.
 */

@Entity
@Table(name = "tag")
public class Tag extends AbstractEntity{
    @NotNull
    @Column(unique = true)
    private String tag;

    @ManyToMany(mappedBy="tags", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("tags")
    private Set<Place> places = new HashSet<Place>();

    public Tag(String tag){
        Assert.hasText(tag,"TagService must not be null or empty!");
        this.tag = tag;
    }

    public Tag(){

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }
}
