package br.com.project.ws.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dekkz on 07/05/17.
 */
@Entity
@Table(name = "place")
public class Place extends AbstractEntity {

    @NotNull
    @Column(unique = true)
    private String nome;

    @ManyToMany(cascade={ CascadeType.ALL})
    @JoinTable(name="place_tag",
    joinColumns={@JoinColumn(name="place_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="tag_id", referencedColumnName="id")})


    @JsonIgnoreProperties("places")
    private Set<Tag> tags = new HashSet<Tag>();

    public Place(String nome){
        Assert.hasText(nome, "Place must not be null or empty!");
        this.nome = nome;
    }

    public Place(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
