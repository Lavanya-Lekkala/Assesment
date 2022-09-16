package com.assessment.service;

import com.assessment.model.ScrapeRequest;
import com.assessment.model.WebScrap;

public interface WebScrapService {
    WebScrap perfromWebScrap(ScrapeRequest scrapeRequest);
}
