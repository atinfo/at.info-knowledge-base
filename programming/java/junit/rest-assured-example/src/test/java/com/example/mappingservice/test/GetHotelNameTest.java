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

public class GetHotelNameTest {

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
    public void testGetHotelNameRustiqueCottagesCuckoo() {

        List<String> hotelName =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Rustique Cottages Cuckoo")
                        .get("/hotel/name")
                        .then()
                        .extract().path("hotelName");

        for (String s : hotelName) {
            assertThat(s, equalTo("Rustique Cottages Cuckoo And Pic Cottages"));
        }
    }


    @Test
    public void testGetHotelNameRustiqueCottagesCuckooId() {

        List<String> hotelId =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Rustique Cottages Cuckoo")
                        .get("/hotel/name")
                        .then()
                        .extract().path("hotelId");

        for (String s : hotelId) {
            assertThat(s, equalTo("HO501636BI"));
        }
    }

    @Test
    public void testGetHotelNameRushmere() {

        List<String> hotelId =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Rushmere")
                        .param("countyrid", "CO350220RU")
                        .get("/hotel/name")
                        .then()
                        .extract().path("hotelId");

        for (String s : hotelId) {
            assertThat(s, equalTo("HO685595NO"));
        }
    }

    @Test
    public void testGetHotelNameRushhouseRF() {

        List<String> hotelName =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Rushhouse")
                        .param("countyrid", "CO350220RU")
                        .get("/hotel/name")
                        .then()
                        .extract().path("hotelName");

        for (String s : hotelName) {
            assertThat(s, equalTo("Rushhouse"));
        }
    }

    @Test
    public void testGetHotelNameRushotelCI266088ZZ() {

        List<String> hotelName =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Rush")
                        .param("countyrid", "CO350220RU")
                        .param("cityid", "CI266088ZZ")
                        .get("/hotel/name")
                        .then()
                        .extract().path("hotelName");

        for (String s : hotelName) {
            assertThat(s, equalTo("Rushotel"));
        }
    }
}
