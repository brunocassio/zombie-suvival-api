package com.zombiesurvival.repositories;

import com.zombiesurvival.domain.Survivor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by bru9isk on 02/01/17.
 */
public interface SurvivorRepository extends CrudRepository<Survivor, Long> {

    @Query("select s from Survivor s")
    List<Survivor> listAllSurvivor();
}
