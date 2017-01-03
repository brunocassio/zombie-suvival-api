package com.zombiesurvival.bootstrap;

import com.zombiesurvival.domain.Inventory;
import com.zombiesurvival.domain.Survivor;
import com.zombiesurvival.repositories.SurvivorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by bru9isk on 02/01/17.
 */
@Component
public class SurvivorLoader implements ApplicationListener<ContextRefreshedEvent> {

    private SurvivorRepository survivorRepository;

    @Autowired
    public void setSurvivorRepository(SurvivorRepository survivorRepository) {
        this.survivorRepository = survivorRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        addSurvivor();

    }

    private void addInventory(){
    }

    private void addSurvivor(){
        Survivor survivor1 = new Survivor();
        survivor1.setName("Bruno");
        survivor1.setAge(30);
        survivor1.setCreatedAt(new Date());
        survivor1.setUpdatedAt(new Date());
        survivor1.setGender("M");
        survivor1.setInfected(Boolean.FALSE);
        survivor1.setLonlat("POINT (-26.431228064506428 -45.17578125)");
        survivorRepository.save(survivor1);

        Survivor survivor2 = new Survivor();
        survivor2.setName("Adriana");
        survivor2.setAge(28);
        survivor2.setCreatedAt(new Date());
        survivor2.setUpdatedAt(new Date());
        survivor2.setGender("F");
        survivor2.setInfected(Boolean.FALSE);
        survivor2.setLonlat("POINT (-55.963749481121276 -68.1991446018219)");
        survivorRepository.save(survivor2);
    }
}
