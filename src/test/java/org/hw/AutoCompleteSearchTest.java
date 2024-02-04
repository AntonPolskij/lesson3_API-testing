package org.hw;

import org.example.AccuweatherAbstractTest;
import org.example.seminar.accuweather.location.Location;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AutoCompleteSearchTest extends AccuweatherAbstractTest {
    @Test
    void autoCompleteSearchTestResponse(){
        Map<String, String> mapQuery = new HashMap<>();
        mapQuery.put("apikey", getApiKey());
        mapQuery.put("q","50");
        List<Location> locations = given()
                .queryParams(mapQuery)
                .pathParam("version", "v1")
                .when().get(getBaseUrl() + "/locations/{version}/cities/autocomplete")
                .then()
                .statusCode(200).time(Matchers.lessThan(2000l))
                .extract().response().jsonPath().getList(".",Location.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals(10,locations.size()),
                () -> Assertions.assertEquals("India",locations.get(2).getCountry().getLocalizedName()),
                () -> Assertions.assertEquals("Rajasthan",locations.get(4).getAdministrativeArea().getLocalizedName())
        );
    }
}
