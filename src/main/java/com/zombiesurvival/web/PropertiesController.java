package com.zombiesurvival.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.domain.Inventory;
import com.zombiesurvival.services.PropertiesService;
import com.zombiesurvival.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bru9isk on 04/01/17.
 */
@RestController
public class PropertiesController {

    private PropertiesService propertiesService;

    @Autowired
    public void setPropertiesService(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/api/people/{id}/properties.json",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public Iterable<Inventory> fetchAllItems(@PathVariable Long id){

        Iterable<Inventory> list = propertiesService.fetchAllItems(id);
        return list;
    }
}
