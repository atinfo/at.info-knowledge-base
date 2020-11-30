package com.connectflexi.mappingservice.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@Ignore public class GetCityNameandcodeTest {

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
    public void testGetCityNameandcodeWithoutParams() {
        //Проверяем доступность сервиса и вызов метода без параметров
        response =
                given()
                        .spec(spec)
                        .when()
                        .get("/city/nameandcode")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        jsonAsString = response.asString();
        assertEquals(jsonAsString, "[]");
    }

    @Test
    public void testGetCityNameandcodeForNameBrusson() {

        List<String> hotelsNames =
                given()
                        .spec(spec)
                .when()
                        .param("query", "Brusson")
                        .get("/city/nameandcode")
                .then()
                        .extract().path("name");

        for (String s : hotelsNames){
            assertThat(s, equalTo("Brusson"));
        }

    }
    @Test
    public void testGetCityNameandcodeForCodeCI913798BR() {

        List<String> hotelsIds =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Brusson")
                        .get("/city/nameandcode")
                        .then()
                        .extract().path("id");

        for (String s : hotelsIds){
            assertThat(s, equalTo("CI913798BR"));
        }

    }

}
