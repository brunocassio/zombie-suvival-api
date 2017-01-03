package com.zombiesurvival.web;

import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.services.SurvivorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/api/people.json",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public Iterable<Survivor> fetchesAllSurvivor(){
        Iterable<Survivor> list = survivorService.fetchesAllSurvivor();
        return list;
    }
}
