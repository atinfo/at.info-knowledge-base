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
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GetIslandTest {

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
    public void testGetIslandForNameMare(){

        List<String> islandName =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Mare")
                        .get("/island")
                        .then()
                        .extract().path("name");

        for (String s : islandName) {
            assertThat(s, equalTo("Mare"));
        }
    }

    @Test
    public void testGetIslandForIdIS745193MA(){

        List<String> islandId =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Mare")
                        .get("/island")
                        .then()
                        .extract().path("id");

        for (String s : islandId) {
            assertThat(s, equalTo("IS745193MA"));
        }
    }

    @Test
    public void testGetIslandForCountryIdCO082496FR(){

        List<String> countryId =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Mare")
                        .get("/island")
                        .then()
                        .extract().path("countryId");

        for (String s : countryId) {
            assertThat(s, equalTo("CO082496FR"));
        }
    }


    @Test
    public void testGetIslandForCountryNameFrance(){

        List<String> countryId =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Mare")
                        .get("/island")
                        .then()
                        .extract().path("countryName");

        for (String s : countryId) {
            assertThat(s, equalTo("France"));
        }
    }
    
    @Test
    public void testGetIslandForRegionIdRE370583NO(){

        List<String> regionId =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Mare")
                        .get("/island")
                        .then()
                        .extract().path("regionId");

        for (String s : regionId) {
            assertThat(s, equalTo("RE370583NO"));
        }
    }
    
    @Test
    public void testGetIslandForRegionNameNewCaledonia(){

        List<String> regionName =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Mare")
                        .get("/island")
                        .then()
                        .extract().path("regionName");

        for (String s : regionName) {
            assertThat(s, equalTo("New Caledonia"));
        }
    }

}
