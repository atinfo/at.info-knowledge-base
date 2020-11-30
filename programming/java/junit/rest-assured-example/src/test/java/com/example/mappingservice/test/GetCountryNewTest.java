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

public class GetCountryNewTest {

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
    public void testGetCountryNameGermany(){

        List<String> countryName =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Germany")
                        .get("/country/new")
                        .then()
                        .extract().path("name");

        for (String s : countryName) {
            assertThat(s, equalTo("Germany"));
        }
    }

    @Test
    public void testGetCountryIdCO228717DE(){

        List<String> countryId =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Germany")
                        .get("/country/new")
                        .then()
                        .extract().path("id");

        for (String s : countryId) {
            assertThat(s, equalTo("CO228717DE"));
        }
    }

    @Test
    public void testGetCountryIso2DE(){

        List<String> iso2 =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Germany")
                        .get("/country/new")
                        .then()
                        .extract().path("iso2");

        for (String s : iso2) {
            assertThat(s, equalTo("DE"));
        }
    }

    @Test
    public void testGetCountryIso3DEU(){

        List<String> iso3 =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Germany")
                        .get("/country/new")
                        .then()
                        .extract().path("iso3");

        for (String s : iso3) {
            assertThat(s, equalTo("DEU"));
        }
    }

    @Test
    public void testGetCountryPhoneCode49(){

        List<String> phoneCode =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Germany")
                        .get("/country/new")
                        .then()
                        .extract().path("phoneCode");

        for (String s : phoneCode) {
            assertThat(s, equalTo("49"));
        }
    }

    @Test
    public void testGetCountryCurrencyCodeRUB(){

        List<String> currencyCode =
                given()
                        .spec(spec)
                        .when()
                        .param("query", "Rus")
                        .get("/country/new")
                        .then()
                        .extract().path("currencyCode");

        for (String s : currencyCode) {
            assertThat(s, equalTo("RUB"));
        }
    }
}
