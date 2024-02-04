package org.example;

import org.example.seminar.accuweather.location.Location;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class LocationTopCitiesTest extends AccuweatherAbstractTest{
    @Test
    void testGetResponse() {
        List<Location> locations = given()
                .queryParam("apikey",getApiKey())
                .queryParam("q", "Moscow")
                .queryParam("offset", 5)
                .pathParam("version", "v1")
                .when()
                .get(getBaseUrl() + "/locations/{version}/cities/search/")
                .then().log().ifValidationFails()
                .statusCode(200).time(Matchers.lessThan(3000L))
                .extract().response().body().jsonPath().getList(".", Location.class);
        Assertions.assertAll(() -> Assertions.assertEquals(24,locations.size()
        ),() -> Assertions.assertEquals("North America",locations.get(1).getRegion().getLocalizedName()));
//        Assertions.assertEquals(23,locations.size()
//        );
//        Assertions.assertEquals("North America",locations.get(1).getRegion().getLocalizedName());
    }
}
