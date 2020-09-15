package com.vr1sh.CoronavirusTracker.controllers;

import com.vr1sh.CoronavirusTracker.models.LocationStats;
import com.vr1sh.CoronavirusTracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getStats();
        model.addAttribute("locationStats", coronaVirusDataService.getStats());
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "home";
    }

}
