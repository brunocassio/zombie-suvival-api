package com.zombiesurvival.repositories;

import com.zombiesurvival.domain.Inventory;
import com.zombiesurvival.domain.Survivor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by bru9isk on 02/01/17.
 */
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    @Query("select i from Inventory i where i.survivor.id = :id ")
    Iterable<Inventory> fetchAllItemsById(@Param("id") Long id);

}
