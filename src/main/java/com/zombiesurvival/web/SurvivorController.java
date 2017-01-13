package com.zombiesurvival.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.beans.SurvivorBean;
import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.services.SurvivorService;
import com.zombiesurvival.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zombiesurvival.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by bru9isk on 02/01/17.
 */
@RestController
public class SurvivorController {

    private SurvivorService survivorService;

    @Autowired
    public void setSurvivorService(SurvivorService survivorService) {
        this.survivorService = survivorService;
    }

    @RequestMapping(value = "/api/people/{id}/report_infection.json",
                    method = RequestMethod.POST,
                    produces = "application/json")
    public ResponseEntity<String> flagSurvivorAsInfected(@RequestBody Long idInfected, @PathVariable Long id){
        Survivor infectedSurvivor = survivorService.fetchSingleSurvivor(idInfected);
        Survivor survivorMakingRegistration = survivorService.fetchSingleSurvivor(id);

        if(infectedSurvivor != null && survivorMakingRegistration != null){
            if(infectedSurvivor.getDenunciantes() != null){
                infectedSurvivor.getDenunciantes().add(survivorMakingRegistration);

                if(infectedSurvivor.getDenunciantes().size() == 3){
                    infectedSurvivor.setInfected(Boolean.TRUE);
                }
                survivorService.updateSurvivor(infectedSurvivor);
                return new ResponseEntity<>("Survivor registered as infected!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("There is no survivor with this id", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/api/people/{id}.json",
                    method = RequestMethod.PATCH,
                    produces = "application/json")
    public ResponseEntity<Survivor> updateLocation(@RequestBody Survivor survivor){
        Survivor currentSurvivor;
        if(survivor != null && survivor.getId() != null && survivor.getLonlat() != null){
            currentSurvivor = survivorService.getSurvivorById(survivor.getId());
            if(currentSurvivor != null){
                currentSurvivor.setLonlat(survivor.getLonlat());
                currentSurvivor.setUpdated_at(Util.returnFormatedDate(new Date()));
                survivorService.updateLocation(currentSurvivor);
            }else{
                return new ResponseEntity<>(survivor, HttpStatus.NOT_ACCEPTABLE);
            }

        }else{
            return new ResponseEntity<>(survivor, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(currentSurvivor, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/people.json",
                    method = RequestMethod.POST,
                    produces = "application/json")
    public ResponseEntity<Survivor> addSurvivor(@RequestBody Survivor survivor){

        if(survivor != null && survivor.getName() != null && survivor.getGender() != null && survivor.getAge() != null){
            Survivor existingSurvivor = survivorService.getSurvivorByName(survivor.getName());
            if(existingSurvivor != null){
                return new ResponseEntity<>(existingSurvivor, HttpStatus.NOT_ACCEPTABLE);
            }else{
                survivor.setInfected(false);
                survivor.setCreated_at(Util.returnFormatedDate(new Date()));
                survivor.setUpdated_at(Util.returnFormatedDate(new Date()));
                survivor.setInventoryList(Util.returnInventoryList(survivor.getItems(), survivor));
                survivorService.addSurvivor(survivor);
                return new ResponseEntity<>(survivor, HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>(survivor, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/api/people/{id}.json",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public Survivor fetchSingleSurvivor(@PathVariable Long id){
        Survivor survivor = survivorService.fetchSingleSurvivor(id);
        return survivor;
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/api/people.json",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public List<SurvivorBean> fetchesAllSurvivor(){
        SurvivorBean survivorBean;
        List<SurvivorBean> beanList = new ArrayList<>();
        List<Survivor> list = survivorService.listAllSurvivor();

        for(Survivor survivor : list){
            survivorBean =
            new SurvivorBean(
                    survivor.getId().toString(),
                    survivor.getName(),
                    survivor.getAge(),
                    survivor.getGender(),
                    survivor.getLonlat(),
                    survivor.getCreated_at(),
                    survivor.getUpdated_at(),
                    survivor.getInfected()
            );
            beanList.add(survivorBean);
        }
        return beanList;
    }
}
