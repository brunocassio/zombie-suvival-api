package com.zombiesurvival.domain;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by bru9isk on 02/01/17.
 */
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer quantity;

    private Date createdAt;

    private Date updatedAt;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "item_id")
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
}
