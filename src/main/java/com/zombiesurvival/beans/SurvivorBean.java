package com.zombiesurvival.beans;

import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.view.View;

/**
 * Created by bru9isk on 07/01/17.
 */
public class SurvivorBean {

    @JsonView(View.Summary.class)
    private String location;
    @JsonView(View.Summary.class)
    private String name;
    @JsonView(View.Summary.class)
    private Integer age;
    @JsonView(View.Summary.class)
    private String gender;
    @JsonView(View.Summary.class)
    private String lonlat;
    @JsonView(View.Summary.class)
    private String created_at;
    @JsonView(View.Summary.class)
    private String updated_at;
    @JsonView(View.Summary.class)
    private Boolean isInfected;

    public SurvivorBean() {
    }

    public SurvivorBean(String location,
                        String name,
                        Integer age,
                        String gender,
                        String lonlat,
                        String created_at,
                        String updated_at,
                        Boolean isInfected) {
        this.location = "http://localhost:8080/api/people/"+location+".json";
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.lonlat = lonlat;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.isInfected = isInfected;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Boolean getInfected() {
        return isInfected;
    }

    public void setInfected(Boolean infected) {
        isInfected = infected;
    }
}
