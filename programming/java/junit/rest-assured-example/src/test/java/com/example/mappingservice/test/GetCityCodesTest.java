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
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GetCityCodesTest {

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
    public void testGetCityCodesForCO350220RU(){

        List<List<String>> providers =
                given()
                        .spec(spec)
                        .when()
                        .param("cityid", "CI266088ZZ")
                        .param("countryid", "CO350220RU")
                        .get("/city/codes")
                        .then()
                        .extract().path("providers.provider");

        //System.out.println(providers.toString());
        assertThat(providers.get(0), hasItems("hotelbeds", "hospitalitygroup", "abreu", "ostrovok", "bronevik", "tourico", "travco", "teamamerica", "ringtours", "rentalcars", "attica", "itravex", "eltour", "arrigo", "imperatours", "restel", "aviacenter", "meetingpoint", "hboholidays", "a2btransfers", "ntincoming"));
    }

    @Test
    public void testGetCityCodesForCI611846AT_CO106684EG(){

        List<String> countryid =
                given()
                        .spec(spec)
                        .when()
                        .param("cityid", "CI611846AT")
                        .param("countryid", "CO106684EG")
                        .get("/city/codes")
                        .then()
                        .extract().path("id");

        for (String s : countryid) {
            assertThat(s, equalTo("CI611846AT"));
        }
    }
}
