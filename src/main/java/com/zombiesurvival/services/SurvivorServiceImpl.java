package com.zombiesurvival.services;

import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.repositories.SurvivorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bru9isk on 02/01/17.
 */
@Service
public class SurvivorServiceImpl implements SurvivorService {

    private SurvivorRepository survivorRepository;

    @Autowired
    public void setSurvivorRepository(SurvivorRepository survivorRepository) {
        this.survivorRepository = survivorRepository;
    }

    @Override
    public Iterable<Survivor> fetchesAllSurvivor() {
        return survivorRepository.findAll();
    }
}
