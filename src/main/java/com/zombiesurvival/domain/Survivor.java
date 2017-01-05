package com.zombiesurvival.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.view.View;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by bru9isk on 02/01/17.
 */
@Entity
@Table(name = "survivor")
public class Survivor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    private Integer age;

    @JsonView(View.Summary.class)
    private String gender;

    @JsonView(View.Summary.class)
    private String lonlat;

    @JsonView(View.Summary.class)
    private Date createdAt;

    @JsonView(View.Summary.class)
    private Date updatedAt;

    @JsonView(View.Summary.class)
    private Boolean isInfected;

    @OneToMany(mappedBy = "survivor", cascade = {CascadeType.ALL})
    private List<Inventory> inventoryList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLonlat() {
        return lonlat;
    }

    public void setLonlat(String lonlat) {
        this.lonlat = lonlat;
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

    public Boolean getInfected() {
        return isInfected;
    }

    public void setInfected(Boolean infected) {
        isInfected = infected;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
