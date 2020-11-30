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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GetHotelInfoTest {

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
    public void testGetHotelInfo(){
        //Проверяем доступность сервиса и вызов метода без параметров
        response =
                given()
                        .spec(spec)
                        .when()
                        .get("/hotel/info")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        jsonAsString = response.asString();
        assertEquals(jsonAsString, "[]");
    }

    @Test
    public void testGetHotelInfoForHO095281TU(){

        List<String> hotelAddress =
                given()
                        .spec(spec)
                        .when()
                        .param("id", "HO095281TU")
                        .get("/hotel/info")
                        .then()
                        .extract().path("hotelAddress");

        for (String s : hotelAddress) {
            assertThat(s, equalTo("St. Factory,1."));
        }
    }

    @Test
    public void testGetHotelInfoForHO294913YA() {

        List<String> hotelPhone =
                given()
                        .spec(spec)
                        .when()
                        .param("id", "HO294913YA")
                        .get("/hotel/info")
                        .then()
                        .extract().path("hotelPhone");

        for (String s : hotelPhone) {
            assertThat(s, equalTo("123"));
        }
    }

    @Test
    public void testGetHotelInfoForHO294913YAEmail() {

        List<String> hotelEmail =
                given()
                        .spec(spec)
                        .when()
                        .param("id", "HO294913YA")
                        .get("/hotel/info")
                        .then()
                        .extract().path("hotelEmail");

        for (String s : hotelEmail) {
            assertThat(s, equalTo("autotest@nut.com"));
        }
    }
}
