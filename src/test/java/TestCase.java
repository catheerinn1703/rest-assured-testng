import flow.CcTestBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.agentLogin.AgentLoginRequest;

import static io.restassured.RestAssured.*;
import static io.restassured.parsing.Parser.JSON;


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

        System.out.println(response.asPrettyString());
        Assertions.assertEquals(200, response.statusCode());

    }
}
