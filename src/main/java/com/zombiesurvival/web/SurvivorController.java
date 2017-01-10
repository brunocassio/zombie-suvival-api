package com.zombiesurvival.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.beans.SurvivorBean;
import com.zombiesurvival.domain.Inventory;
import com.zombiesurvival.domain.Item;
import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.services.SurvivorService;
import com.zombiesurvival.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


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

//    public ResponseEntity<Survivor> flagSurvivorAsInfected(){
//
//    }

    @RequestMapping(value = "/api/people/{id}.json",
                    method = RequestMethod.PATCH,
                    produces = "application/json")
    public ResponseEntity<Survivor> updateLocation(@RequestBody Survivor survivor){
        Survivor currentSurvivor;
        if(survivor != null && survivor.getId() != null && survivor.getLonlat() != null){
            currentSurvivor = survivorService.getSurvivorById(survivor.getId());
            if(currentSurvivor != null){
                currentSurvivor.setLonlat(survivor.getLonlat());
                currentSurvivor.setUpdated_at(returnFormatedDate(new Date()));
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
                survivor.setCreated_at(returnFormatedDate(new Date()));
                survivor.setUpdated_at(returnFormatedDate(new Date()));
                survivor.setInventoryList(returnInventoryList(survivor.getItems(), survivor));
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

    private String returnFormatedDate(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ms'Z'");
        return df.format(date);
    }

    private List<Inventory> returnInventoryList(Map<String, String> items, Survivor survivor){
        Inventory inventory;
        List<Inventory> listOfInventory = new ArrayList<>();
        List<String> listOfItems = new ArrayList<>(items.keySet());
        List<String> listOfQuantities = new ArrayList<>(items.values());

        if(listOfItems.size() == listOfQuantities.size()){
            for(int i = 0; i < listOfItems.size(); i++){
                Integer quantity = Integer.valueOf(listOfQuantities.get(i));
                String itemName = listOfItems.get(i);

                inventory = new Inventory();
                inventory.setCreated_at(returnFormatedDate(new Date()));
                inventory.setUpdated_at(returnFormatedDate(new Date()));
                inventory.setQuantity(quantity);

                inventory.setItem(returnItem(itemName, quantity));
                inventory.setSurvivor(survivor);
                listOfInventory.add(inventory);
            }
        }
        return listOfInventory;
    }

    private Item returnItem(String itemName, Integer quantity){
        int totalPoits;
        switch (itemName){
            case "Water":
                totalPoits = Item.WATER_POINTS * quantity;
                return new Item(totalPoits, itemName);
            case "Food":
                totalPoits = Item.FOOD_POINTS * quantity;
                return new Item(totalPoits, itemName);
            case "Medication":
                totalPoits = Item.MEDICATION_POINTS * quantity;
                return new Item(totalPoits, itemName);
            case "Ammunition":
                totalPoits = Item.AMMUNITION_POINTS * quantity;
                return new Item(totalPoits, itemName);
            default:
                return null;
        }
    }
}
