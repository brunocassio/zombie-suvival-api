package com.zombiesurvival.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.beans.TradeBean;
import com.zombiesurvival.domain.Inventory;
import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.services.PropertiesService;
import com.zombiesurvival.services.SurvivorService;
import com.zombiesurvival.util.Util;
import com.zombiesurvival.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bru9isk on 04/01/17.
 */
@RestController
public class PropertiesController {

    private PropertiesService propertiesService;

    private SurvivorService survivorService;

    @Autowired
    public void setSurvivorService(SurvivorService survivorService) {
        this.survivorService = survivorService;
    }

    @Autowired
    public void setPropertiesService(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @RequestMapping(value = "/api/people/{id}/properties/trade_item.json",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity<String> makeTransaction(@RequestBody TradeBean trade,
                                                  @PathVariable Long id){

        Survivor provider = survivorService.getSurvivorById(id);
        Survivor consumer = survivorService.getSurvivorByName(trade.getName());

        List<Inventory> itemsWantedList = Util.returnInventoryList(trade.getItemsWanted(), consumer);
        List<Inventory> payInReturnList = Util.returnInventoryList(trade.getPayInReturn(), provider);

        if(isTotalPointsEqual(itemsWantedList, payInReturnList, consumer, provider)){

            consumer.setInventoryList(payInReturnList);
            provider.setInventoryList(itemsWantedList);
            survivorService.updateSurvivor(consumer);
            survivorService.updateSurvivor(provider);
            return new ResponseEntity<>("ok", HttpStatus.OK);

        }else{
            return new ResponseEntity<>("not ok", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private Boolean isTotalPointsEqual(List<Inventory> itemsWantedList, List<Inventory> payInReturnList, Survivor consumer, Survivor provider){
        Integer pointsItemWanted = 0;
        Integer pointsPayReturn = 0;
        Integer pointsConsumer = 0;
        Integer pointsProvider = 0;

        for(Inventory providerInventory : provider.getInventoryList()){
            pointsProvider += providerInventory.getItem().getPoints();
        }

        for(Inventory consumerInvetory : consumer.getInventoryList()){
            pointsConsumer += consumerInvetory.getItem().getPoints();
        }

        for(Inventory itemWanted : itemsWantedList){
            pointsItemWanted += itemWanted.getItem().getPoints();
        }

        for(Inventory payInReturn : payInReturnList){
            pointsPayReturn += payInReturn.getItem().getPoints();
        }

        if(pointsItemWanted == pointsPayReturn && pointsProvider <= pointsConsumer){
            return true;
        }else{
            return false;
        }
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
