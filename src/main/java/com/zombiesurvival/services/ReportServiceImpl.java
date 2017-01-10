package com.zombiesurvival.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bru9isk on 09/01/17.
 */
@Service
public class ReportServiceImpl implements ReportService {

    private SurvivorService survivorService;

    @Autowired
    public void setSurvivorService(SurvivorService survivorService) {
        this.survivorService = survivorService;
    }

    @Override
    public Double getAverageInfectedPeople() {
        Double total = survivorService.getTotalSurvivor();
        Double totalInfected = survivorService.getTotalInfected();
        return (totalInfected / total) * 100;
    }

    @Override
    public Double getAverageNonInfectedPeople() {
        Double total = survivorService.getTotalSurvivor();
        Double totalNonInfected = survivorService.getTotalNonInfected();
        return (totalNonInfected / total) * 100;
    }
}
