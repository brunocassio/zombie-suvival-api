package com.zombiesurvival.repositories;

import com.zombiesurvival.domain.Inventory;
import com.zombiesurvival.domain.Survivor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bru9isk on 02/01/17.
 */
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
}
