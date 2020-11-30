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

public class GetHotelcategoryTest {
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
    public void testGetHotelcategory1star(){

        List<String> hotelName =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "1*")
                        .get("/hotelcategory")
                        .then()
                        .extract().path("id");

        for (String s : hotelName) {
            assertThat(s, equalTo("HC861464ZZ"));
        }
    }

    @Test
    public void testGetHotelcategory1starHC861464ZZ(){

        List<String> hotelName =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "1*")
                        .get("/hotelcategory")
                        .then()
                        .extract().path("name");

        for (String s : hotelName) {
            assertThat(s, equalTo("1*"));
        }
    }

    @Test
    public void testGetHotelcategoryHC793507ZZ(){

        List<String> hotelName =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "2* S")
                        .get("/hotelcategory")
                        .then()
                        .extract().path("id");

        for (String s : hotelName) {
            assertThat(s, equalTo("HC793507ZZ"));
        }
    }
}
