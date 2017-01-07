package com.zombiesurvival.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.view.View;

import javax.persistence.*;

/**
 * Created by bru9isk on 02/01/17.
 */
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonView(View.Summary.class)
    private Integer points;

    @JsonView(View.Summary.class)
    private String name;

    @OneToOne(mappedBy = "item")
    private Inventory inventory;

    public Item() {
    }

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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
