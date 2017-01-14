package com.zombiesurvival.services;

import com.zombiesurvival.domain.Inventory;

import java.util.List;

/**
 * Created by bru9isk on 04/01/17.
 */
public interface PropertiesService {

    Iterable<Inventory> fetchAllItems(Long id);
    void removeInventory(Inventory inventory);
}
