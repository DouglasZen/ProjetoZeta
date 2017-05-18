package br.com.projetozeta.ws.domain;

import java.util.Date;

/**
 * Created by dekkz on 17/05/17.
 */

public class ResultadoPlaceTag {
    private User user;
    private Place place;
    private Tag tag;
    private Date date;

    public ResultadoPlaceTag(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
