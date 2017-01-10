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

    @Query("select s from Survivor s where s.name = ?#{[0]}")
    Survivor getSurvivorByName(String name);

    @Query("select count(s) from Survivor s")
    Double getTotalSurvivor();

    @Query("select count(s) from Survivor s where s.isInfected = true")
    Double getTotalOfInfected();

    @Query("select count(s) from Survivor s where s.isInfected = false")
    Double getTotalNonInfected();
}
