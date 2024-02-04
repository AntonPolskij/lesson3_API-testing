package org.hw;

import org.example.AccuweatherAbstractTest;
import org.example.seminar.accuweather.Weather;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

public class DayofDailyForecastsTest extends AccuweatherAbstractTest {

    @Test
    void dayOfDailyForecastsTestResponse() {
        Weather weather = given()
                .queryParam("apikey", getApiKey())
                .pathParams("locationId", 50, "version", "v1")
                .when()
                .get(getBaseUrl() + "/forecasts/{version}/daily/1day/{locationId}")
                .then()
                .statusCode(200).time(Matchers.lessThan(2000L))
                .extract().response().body().as(Weather.class);

        Assertions.assertEquals(34, weather.getDailyForecasts().get(0).getTemperature().getMaximum().getValue());
    }
}
