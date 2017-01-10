package com.zombiesurvival.web;

import com.zombiesurvival.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bru9isk on 09/01/17.
 */
@RestController
public class ReportController {

    private ReportService reportService;

    @Autowired
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/api/report/infected.json",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public Double averageInfectedPeopleReport(){
        return reportService.getAverageInfectedPeople();
    }

    @RequestMapping(value = "/api/report/non_infected.json",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public Double nonInfectedPeopleReport(){
        return reportService.getAverageNonInfectedPeople();
    }

}
