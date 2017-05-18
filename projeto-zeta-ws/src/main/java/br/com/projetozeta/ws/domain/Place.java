package br.com.projetozeta.ws.domain;

import java.io.Serializable;

/**
 * Created by dekkz on 16/05/17.
 */
public class Place implements Serializable {
    private Long id;
    private String nome;

    public Place(){

    }

    public Place(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
