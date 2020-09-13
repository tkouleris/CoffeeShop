package com.tkouleris.coffeeshop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private long id;

    private String item;

    private int price;

    private boolean active;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    List<Orders> order;

    private String image_name;

    @javax.persistence.Transient
    public String image_url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImageURL(String baseURL)
    {
        return baseURL + "/upload/" + getImage_name();
    }
}
