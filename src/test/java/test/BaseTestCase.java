package test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTestCase {
    public String baseUrl(){
        return RestAssured.baseURI = "http://34.102.228.199";
    }

    @BeforeTest
    public void logging(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
