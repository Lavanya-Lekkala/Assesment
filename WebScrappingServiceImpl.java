package com.assessment.service;

import com.assessment.model.Recipe;
import com.assessment.model.ScrapeRequest;
import com.assessment.model.WebScrap;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebScrappingServiceImpl implements WebScrapService {

    @Override
    public WebScrap perfromWebScrap(ScrapeRequest scrapeRequest) {
        WebScrap scrap = new WebScrap();
        Recipe recipe = new Recipe();
        recipe.setIngredients(new ArrayList<>());
        recipe.setInstructions(new ArrayList<>());
        scrap.setRecipe(recipe);
        try (final WebClient webClient = new WebClient()) {
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            final HtmlPage page = webClient.getPage(scrapeRequest.getUrl());

            List<HtmlListItem> listItems = page.getByXPath("//ul[@class='list--bullets']/li");
            for (HtmlListItem list: listItems ) {
                recipe.getIngredients().add(list.getFirstChild().getVisibleText());
            }

            List<HtmlListItem> orderedItems = page.getByXPath("//ol[@class='list--numbers']/li");
            for (HtmlListItem list: orderedItems ) {
                recipe.getInstructions().add(list.getFirstElementChild().getVisibleText());
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return scrap;
    }
}
