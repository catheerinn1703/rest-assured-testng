package flow;

import io.restassured.RestAssured;

public class BaseUrl {

    public String baseUrl(){
        return RestAssured.baseURI = "https://sqecc-be.stg.squantumengine.com";
    }
}
