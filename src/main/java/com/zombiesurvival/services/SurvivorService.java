package com.zombiesurvival.services;

import com.zombiesurvival.domain.Survivor;

import java.util.List;

/**
 * Created by bru9isk on 02/01/17.
 */
public interface SurvivorService {

    Iterable<Survivor> fetchesAllSurvivor();
    List<Survivor> listAllSurvivor();
    Survivor fetchSingleSurvivor(Long id);
    void addSurvivor(Survivor survivor);
}
