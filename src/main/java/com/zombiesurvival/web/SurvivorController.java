package com.zombiesurvival.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.zombiesurvival.beans.SurvivorBean;
import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.services.SurvivorService;
import com.zombiesurvival.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @RequestMapping(value = "/api/people.json",
                    method = RequestMethod.POST,
                    produces = "application/json")
    public ResponseEntity<Survivor> addSurvivor(@RequestBody Survivor survivor){

        return new ResponseEntity<Survivor>(survivor, HttpStatus.OK);
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/api/people/{id}.json",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public Survivor fetchSingleSurivor(@PathVariable Long id){
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
