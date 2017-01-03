package com.zombiesurvival.services;

import com.zombiesurvival.domain.Survivor;

/**
 * Created by bru9isk on 02/01/17.
 */
public interface SurvivorService {

    Iterable<Survivor> fetchesAllSurvivor();
}
