package com.zombiesurvival.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.zombiesurvival.beans.TradeBean;
import com.zombiesurvival.domain.Inventory;
import com.zombiesurvival.domain.Item;
import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.services.PropertiesService;
import com.zombiesurvival.services.SurvivorService;
import com.zombiesurvival.util.Util;
import com.zombiesurvival.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        List<Inventory> itemsToRemoveFromProvider = new ArrayList<>();
        for(Inventory payInReturn : payInReturnList){
            Item itemPayInReturn = payInReturn.getItem();
            for(Inventory providerInventory : provider.getInventoryList()){
                Item providerItem = providerInventory.getItem();
                if(itemPayInReturn.getName().equals(providerItem.getName()) &&
                        itemPayInReturn.getPoints() == providerItem.getPoints()){
                    itemsToRemoveFromProvider.add(providerInventory);
                }
            }
        }

        List<Inventory> itemsToRemoveFromConsumer = new ArrayList<>();
        for(Inventory inventoryWanted : itemsWantedList){
            Item itemWanted = inventoryWanted.getItem();
            for(Inventory consumerInventory : consumer.getInventoryList()){
                Item consumerItem = consumerInventory.getItem();
                if(itemWanted.getName().equals(consumerItem.getName()) &&
                        itemWanted.getPoints() == consumerItem.getPoints()){
                    itemsToRemoveFromConsumer.add(consumerInventory);
                }
            }
        }

        if(itemsToRemoveFromConsumer.size() > 0 && itemsToRemoveFromProvider.size() > 0){
            List<Inventory> itemsAddToConsumer;
            List<Inventory> itemsAddToProvider;

            itemsAddToConsumer = itemsToRemoveFromProvider;
            itemsAddToProvider = itemsToRemoveFromConsumer;

            for(Inventory ic : itemsToRemoveFromConsumer){
                propertiesService.removeInventory(ic);
            }

            for(Inventory ic : itemsToRemoveFromProvider){
                propertiesService.removeInventory(ic);
            }

            for(Inventory it : itemsAddToConsumer){
                consumer.getInventoryList().add(it);
                survivorService.updateSurvivor(consumer);
            }

            for(Inventory it : itemsAddToProvider){
                provider.getInventoryList().add(it);
                survivorService.updateSurvivor(provider);
            }
        }else{
            return new ResponseEntity<>("There is no item to change!", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Transaction done!", HttpStatus.OK);
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
