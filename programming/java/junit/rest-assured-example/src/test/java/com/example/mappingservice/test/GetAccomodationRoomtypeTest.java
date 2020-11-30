package com.connectflexi.mappingservice.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GetAccomodationRoomtypeTest {
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
    public void testGetAccomodationRoomtype(){

        //Проверяем доступность сервиса и вызов метода без параметров
        response =
                given()
                        .spec(spec)
                .when()
                        .param("id", "")
                        .param("query", "")
                        .get("/accommodation/roomtype")
                .then()
                        .statusCode(200)
                        .extract()
                        .response();
        jsonAsString = response.asString();
        assertEquals(jsonAsString, "[]");
    }

    @Test
    public void testGetAccomodationRoomtypeForHO692419AY(){

        List<String> expectedRoomTypeIds = Arrays.asList("RO916898GR", "RO232550GR", "RO235422GR", "RO486009GR", "RO854194GR", "RO627240GR", "RO425898GR", "RO770789GR", "RO128711GR");

        List<String> roomTypeIds =
                given()
                        .spec(spec)
                        .when()
                        .param("id", "HO692419AY")
                        .param("query", "")
                        .get("/accommodation/roomtype")
                        .then()
                        .extract().path("id");


        assertThat(roomTypeIds, is(expectedRoomTypeIds));

    }

    @Test
    public void testGetAccomodationRoomtypeForHO692419AYStandardSeaView(){

        List<String> roomTypeIds =
                given()
                        .spec(spec)
                        .when()
                        .param("id", "HO692419AY")
                        .param("query", "Standard Sea View")
                        .get("/accommodation/roomtype")
                        .then()
                        .extract().path("id");

        for (String s : roomTypeIds){
            assertThat(s, equalTo("RO574343GR"));
        }
    }

}
