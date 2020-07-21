package com.tkouleris.coffeeshop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tables {
    @Id
    @GeneratedValue
    private long id;

    private String table_code;

    private boolean active;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "table_id")
    List<Orders> order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTable_code() {
        return table_code;
    }

    public void setTable_code(String table_code) {
        this.table_code = table_code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
