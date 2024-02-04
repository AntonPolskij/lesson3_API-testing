package org.hw;

import org.example.AccuweatherAbstractTest;
import org.example.seminar.accuweather.Weather;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TenDaysofDailyForecastsTest extends AccuweatherAbstractTest {
    @Test
    void testGetResponse() {
        Weather weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50).when().get(getBaseUrl() + "/forecasts/v1/daily/10day/{locationKey}").then().statusCode(200).time(Matchers.lessThan(2000L)).extract().response().body().as(Weather.class);

        Assertions.assertEquals(10, weather.getDailyForecasts().size());
        Assertions.assertEquals(0,weather.getDailyForecasts().get(1).getTemperature().getMinimum().getValue());
    }
}

