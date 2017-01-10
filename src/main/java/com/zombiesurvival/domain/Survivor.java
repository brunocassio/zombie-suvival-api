package com.zombiesurvival.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.view.View;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

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

    private Boolean isInfected;

    @JsonView(View.Summary.class)
    private String created_at;

    @JsonView(View.Summary.class)
    private String updated_at;

    @OneToMany(mappedBy = "survivor", cascade = {CascadeType.ALL})
    private List<Inventory> inventoryList;

    @ElementCollection
    private Map<String, String> items;

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

    public Map<String, String> getItems() {
        return items;
    }

    public void setItems(Map<String, String> items) {
        this.items = items;
    }

}
