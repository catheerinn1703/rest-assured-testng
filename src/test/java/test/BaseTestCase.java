package test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeTest;

public class BaseTestCase {
    public String baseUrl(){
        return RestAssured.baseURI = "https://sqecc-be.stg.squantumengine.com";
    }

    @BeforeTest
    public void logging(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
