package com.connectflexi.mappingservice.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetCountryIslandByCityTest {
    private static RequestSpecification spec;
    public Response response;
    public static String jsonAsString;

    @BeforeClass
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://example.com")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void testGetCountryIslandByCI266088ZZ(){

        String countryId =
                given()
                        .spec(spec)
                        .when()
                        .param("cityId", "CI266088ZZ")
                        .get("city/countryid")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("countryId");
        assertEquals(countryId, "CO350220RU");

    }
}
