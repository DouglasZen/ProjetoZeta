package br.com.projetozeta.ws.domain;

import java.io.Serializable;

/**
 * Created by dekkz on 16/05/17.
 */
public class Tag implements Serializable {

    private Long id;
    private String tag;

    public Tag(){

    }

    public Tag(Long id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
