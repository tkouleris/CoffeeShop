package com.tkouleris.coffeeshop.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client_table")
public class Tables {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="table_code")
    private String tableCode;

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
        return tableCode;
    }

    public void setTable_code(String tableCode) {
        this.tableCode = tableCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
