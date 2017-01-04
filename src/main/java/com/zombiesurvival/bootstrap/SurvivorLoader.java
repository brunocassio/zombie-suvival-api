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

    private List<Inventory> returnInventory(){
        List<Inventory> inventories = new ArrayList<>();

        Inventory water = new Inventory();
        water.setUpdatedAt(new Date());
        water.setCreatedAt(new Date());
        water.setQuantity(1);
        Item waterItem = new Item(4, "Water");
        itemRepository.save(waterItem);
        water.setItem(waterItem);
        inventoryRepository.save(water);

        Inventory food = new Inventory();
        food.setUpdatedAt(new Date());
        food.setCreatedAt(new Date());
        food.setQuantity(1);
        Item foodItem = new Item(3, "Food");
        itemRepository.save(foodItem);
        food.setItem(foodItem);
        inventoryRepository.save(food);

        Inventory medication = new Inventory();
        medication.setUpdatedAt(new Date());
        medication.setCreatedAt(new Date());
        medication.setQuantity(1);
        Item medicationItem = new Item(2, "Medication");
        itemRepository.save(medicationItem);
        medication.setItem(medicationItem);
        inventoryRepository.save(medication);

        Inventory ammunition = new Inventory();
        ammunition.setUpdatedAt(new Date());
        ammunition.setCreatedAt(new Date());
        ammunition.setQuantity(1);
        Item ammunitionItem = new Item(1, "Ammunition");
        itemRepository.save(ammunitionItem);
        ammunition.setItem(ammunitionItem);
        inventoryRepository.save(ammunition);

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
        survivor1.setCreatedAt(new Date());
        survivor1.setUpdatedAt(new Date());
        survivor1.setGender("M");
        survivor1.setInfected(Boolean.FALSE);
        survivor1.setLonlat("POINT (-26.431228064506428 -45.17578125)");
        survivor1.setInventoryList(returnInventory());
        survivorRepository.save(survivor1);

        Survivor survivor2 = new Survivor();
        survivor2.setName("Adriana");
        survivor2.setAge(28);
        survivor2.setCreatedAt(new Date());
        survivor2.setUpdatedAt(new Date());
        survivor2.setGender("F");
        survivor2.setInfected(Boolean.FALSE);
        survivor2.setLonlat("POINT (-55.963749481121276 -68.1991446018219)");
        survivor2.setInventoryList(returnInventory());
        survivorRepository.save(survivor2);
    }
}
