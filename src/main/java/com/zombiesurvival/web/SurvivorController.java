package com.zombiesurvival.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.services.SurvivorService;
import com.zombiesurvival.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
    public Iterable<Survivor> fetchesAllSurvivor(){
        Iterable<Survivor> list = survivorService.fetchesAllSurvivor();
        return list;
    }
}
