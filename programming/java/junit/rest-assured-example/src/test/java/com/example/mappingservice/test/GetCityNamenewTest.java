package com.connectflexi.mappingservice.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GetCityNamenewTest {

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
    public void testGetNamenewForNameMoscow() {

        List<String> cityName =
                given()
                        .spec(spec)
                        .when()
                        .param("city", "Moscow")
                        .get("/city/namenew")
                        .then()
                        .extract().path("name");

        for (String s : cityName) {
            assertThat(s, equalTo("Moscow"));
        }
    }

    @Test
    public void testGetNamenewForIdCI266088ZZ() {

        List<String> cityId =
                given()
                        .spec(spec)
                        .when()
                        .param("city", "Moscow")
                        .get("/city/namenew")
                        .then()
                        .extract().path("id");

        for (String s : cityId) {
            assertThat(s, equalTo("CI266088ZZ"));
        }
    }

    @Test
    public void testGetNamenewForCountryIdCO350220RU() {

        List<String> countryId =
                given()
                        .spec(spec)
                        .when()
                        .param("city", "Moscow")
                        .get("/city/namenew")
                        .then()
                        .extract().path("countryId");

        for (String s : countryId) {
            assertThat(s, equalTo("CO350220RU"));
        }
    }

    @Test
    public void testGetNamenewForCountryNameRF() {

        List<String> countyrName =
                given()
                        .spec(spec)
                        .when()
                        .param("city", "Moscow")
                        .get("/city/namenew")
                        .then()
                        .extract().path("countryName");

        for (String s : countyrName) {
            assertThat(s, equalTo("Russian Federation"));
        }
    }

    @Test
    public void testGetNamenewForParisWithCountryId() {

        List<String> countryName =
                given()
                        .spec(spec)
                        .when()
                        .param("city", "Paris")
                        .param("countryid", "CO097322US")
                        .get("/city/namenew")
                        .then()
                        .extract().path("countryName");

        for (String s : countryName) {
            assertThat(s, equalTo("USA_AC"));
        }
    }

    @Test
    public void testGetNamenewForCamaraDeLobosWithIslandId() {

        List<String> islandId =
                given()
                        .spec(spec)
                        .when()
                        .param("city", "Camara de Lobos")
                        .param("islandid", "IS015161MA")
                        .get("/city/namenew")
                        .then()
                        .extract().path("islandName");

        for (String s : islandId) {
            assertThat(s, equalTo("Madeira"));
        }
    }
}
