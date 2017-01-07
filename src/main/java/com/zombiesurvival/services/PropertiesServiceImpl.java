package com.zombiesurvival.services;

import com.zombiesurvival.domain.Inventory;
import com.zombiesurvival.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bru9isk on 04/01/17.
 */
@Service
public class PropertiesServiceImpl implements PropertiesService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Iterable<Inventory> fetchAllItems(Long id) {
        Iterable<Inventory> inventories = inventoryRepository.fetchAllItemsById(id);
        return inventories;
    }
}
