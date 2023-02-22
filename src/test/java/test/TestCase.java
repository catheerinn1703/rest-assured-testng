package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.agentLogin.AgentLoginRequest;

import static io.restassured.RestAssured.*;

public class TestCase {

    @Test
    public void agentLogin() {
        String username = "admin@smma.id";
        String password = "admin";
        baseURI = "http://34.102.228.199";
        basePath= "/v1/agent/login";
        AgentLoginRequest agentLoginRequest = AgentLoginRequest.agentLoginRequest(username, password);

        Response response = given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(agentLoginRequest)
                .post()
                .then()
                .extract().response();


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertNotNull(response.getBody().asString());
    }
}
