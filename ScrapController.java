package com.assessment.controller;

import com.assessment.model.ScrapeRequest;
import com.assessment.model.WebScrap;
import com.assessment.service.WebScrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ScrapController {
    @Autowired
    WebScrapService webScrapService;

    @PostMapping("/scrape")
    public WebScrap getAllBook(@RequestBody ScrapeRequest scrapeRequest) {
        return webScrapService.perfromWebScrap(scrapeRequest);
    }

}
