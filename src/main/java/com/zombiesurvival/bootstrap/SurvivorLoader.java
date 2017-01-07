package com.zombiesurvival.bootstrap;

import com.zombiesurvival.domain.Inventory;
import com.zombiesurvival.domain.Item;
import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.repositories.InventoryRepository;
import com.zombiesurvival.repositories.ItemRepository;
import com.zombiesurvival.repositories.SurvivorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bru9isk on 02/01/17.
 */
@Component
public class SurvivorLoader implements ApplicationListener<ContextRefreshedEvent> {

    private SurvivorRepository survivorRepository;
    private InventoryRepository inventoryRepository;
    private ItemRepository itemRepository;

    @Autowired
    public void setSurvivorRepository(SurvivorRepository survivorRepository) {
        this.survivorRepository = survivorRepository;
    }

    @Autowired
    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        addSurvivor();
    }

    private List<Inventory> returnInventory(Survivor survivor){
        List<Inventory> inventories = new ArrayList<>();

        Inventory water = new Inventory();
        water.setCreated_at(returnFormatedDate(new Date()));
        water.setUpdated_at(returnFormatedDate(new Date()));
        water.setQuantity(1);
        Item waterItem = new Item(4, "Water");
        water.setItem(waterItem);
        water.setSurvivor(survivor);

        Inventory food = new Inventory();
        food.setUpdated_at(returnFormatedDate(new Date()));
        food.setCreated_at(returnFormatedDate(new Date()));
        food.setQuantity(1);
        Item foodItem = new Item(3, "Food");
        food.setItem(foodItem);
        food.setSurvivor(survivor);

        Inventory medication = new Inventory();
        medication.setUpdated_at(returnFormatedDate(new Date()));
        medication.setCreated_at(returnFormatedDate(new Date()));
        medication.setQuantity(1);
        Item medicationItem = new Item(2, "Medication");
        medication.setItem(medicationItem);
        medication.setSurvivor(survivor);

        Inventory ammunition = new Inventory();
        ammunition.setUpdated_at(returnFormatedDate(new Date()));
        ammunition.setCreated_at(returnFormatedDate(new Date()));
        ammunition.setQuantity(1);
        Item ammunitionItem = new Item(1, "Ammunition");
        ammunition.setItem(ammunitionItem);
        ammunition.setSurvivor(survivor);

        inventories.add(water);
        inventories.add(food);
        inventories.add(medication);
        inventories.add(ammunition);

        return inventories;
    }

    private void addSurvivor(){
        Survivor survivor1 = new Survivor();
        survivor1.setName("Bruno");
        survivor1.setAge(30);
        survivor1.setCreated_at(returnFormatedDate(new Date()));
        survivor1.setUpdated_at(returnFormatedDate(new Date()));
        survivor1.setGender("M");
        survivor1.setInfected(Boolean.FALSE);
        survivor1.setLonlat("POINT (-26.431228064506428 -45.17578125)");
        survivor1.setInventoryList(returnInventory(survivor1));
        survivorRepository.save(survivor1);

        Survivor survivor2 = new Survivor();
        survivor2.setName("Adriana");
        survivor2.setAge(28);
        survivor2.setCreated_at(returnFormatedDate(new Date()));
        survivor2.setUpdated_at(returnFormatedDate(new Date()));
        survivor2.setGender("F");
        survivor2.setInfected(Boolean.FALSE);
        survivor2.setLonlat("POINT (-55.963749481121276 -68.1991446018219)");
        survivor2.setInventoryList(returnInventory(survivor2));
        survivorRepository.save(survivor2);
    }

    private String returnFormatedDate(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ms'Z'");
        return df.format(date);
    }
}
