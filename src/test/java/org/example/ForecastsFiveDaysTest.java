package org.example;

import org.example.seminar.accuweather.Weather;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ForecastsFiveDaysTest extends AccuweatherAbstractTest {
    @Test
    void testGetResponse() {
        Weather weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50).when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{locationKey}").then().statusCode(200).time(Matchers.lessThan(10000L)).extract().response().body().as(Weather.class);

        Assertions.assertEquals(5, weather.getDailyForecasts().size());

    }
}
