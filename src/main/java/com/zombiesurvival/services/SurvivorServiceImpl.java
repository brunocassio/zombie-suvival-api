package com.zombiesurvival.services;

import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.repositories.SurvivorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Survivor> listAllSurvivor() {
        return survivorRepository.listAllSurvivor();
    }

    @Override
    public Survivor fetchSingleSurvivor(Long id) {
        return survivorRepository.findOne(id);
    }

    @Override
    public void addSurvivor(Survivor survivor) {
        survivorRepository.save(survivor);
    }

    @Override
    public Survivor getSurvivorByName(String name) {
        return survivorRepository.getSurvivorByName(name);
    }

    @Override
    public void updateLocation(Survivor survivor) {
        survivorRepository.save(survivor);
    }

    @Override
    public Survivor getSurvivorById(Long id) {
        return survivorRepository.findOne(id);
    }

    @Override
    public Double getTotalSurvivor() {
        Double total = survivorRepository.getTotalSurvivor();
        return total;
    }

    @Override
    public Double getTotalInfected() {
        return survivorRepository.getTotalOfInfected();
    }
}
