package com.zombiesurvival.domain;


import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.view.View;

import javax.persistence.*;

/**
 * Created by bru9isk on 02/01/17.
 */
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    private Integer quantity;

    @JsonView(View.Summary.class)
    private String created_at;

    @JsonView(View.Summary.class)
    private String updated_at;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "item_id")
    @JsonView(View.Summary.class)
    private Item item;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "survivor_id")
    private Survivor survivor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Survivor getSurvivor() {
        return survivor;
    }

    public void setSurvivor(Survivor survivor) {
        this.survivor = survivor;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
