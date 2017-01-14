package com.zombiesurvival.util;

import com.zombiesurvival.domain.Inventory;
import com.zombiesurvival.domain.Item;
import com.zombiesurvival.domain.Survivor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by bru9isk on 12/01/17.
 */
public class Util {

    public static Item returnItem(String itemName, Integer quantity){
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

    public static List<Inventory> returnInventoryList(Map<String, String> items, Survivor survivor){
        Inventory inventory;
        List<Inventory> listOfInventory = new ArrayList<>();
        List<String> listOfItems = new ArrayList<>(items.keySet());
        List<String> listOfQuantities = new ArrayList<>(items.values());

        if(listOfItems.size() == listOfQuantities.size()){
            for(int i = 0; i < listOfItems.size(); i++){
                Integer quantity = Integer.valueOf(listOfQuantities.get(i));
                String itemName = listOfItems.get(i);

                inventory = new Inventory();
                inventory.setCreated_at(survivor.getCreated_at());
                inventory.setUpdated_at(returnFormatedDate(new Date()));
                inventory.setQuantity(quantity);

                inventory.setItem(Util.returnItem(itemName, quantity));
                inventory.setSurvivor(survivor);
                listOfInventory.add(inventory);
            }
        }
        return listOfInventory;
    }

    public static String returnFormatedDate(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ms'Z'");
        return df.format(date);
    }
}
