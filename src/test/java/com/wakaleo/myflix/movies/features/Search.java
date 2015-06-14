package com.wakaleo.myflix.movies.features;

import com.wakaleo.myflix.movies.MovieServiceApplication;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/search")
public class Search {
}
