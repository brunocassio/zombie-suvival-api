package com.zombiesurvival.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by bru9isk on 02/01/17.
 */
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer points;

    private String name;

    @OneToMany(mappedBy = "item")
    private List<Inventory> inventoryList;

    public Item(Integer points, String name) {
        this.points = points;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
