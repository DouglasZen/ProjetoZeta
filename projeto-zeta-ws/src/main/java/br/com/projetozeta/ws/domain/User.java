package br.com.projetozeta.ws.domain;

import java.io.Serializable;

/**
 * Created by dekkz on 16/05/17.
 */
public class User implements Serializable{
    private Long id;
    private String nome;
    private String email;
    private String idProvider;

    public User(){

    }

    public User(Long id, String nome, String email, String idProvider) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idProvider = idProvider;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(String idProvider) {
        this.idProvider = idProvider;
    }
}
