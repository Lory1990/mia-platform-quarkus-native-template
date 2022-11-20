package com.sample.app;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class CPStatusTest {

    @Test
    public void heathTest() {
        given()
                .when().get("/-/healthz")
                .then()
                .statusCode(200);
    }

    @Test
    public void readyTest() {
        given()
                .when().get("/-/ready")
                .then()
                .statusCode(200);
    }

    @Test
    public void checkUpTest() {
        given()
                .when().get("/-/check-up")
                .then()
                .statusCode(200);
    }

}