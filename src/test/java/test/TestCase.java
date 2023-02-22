package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.agentLogin.AgentLoginRequest;
import pojo.agentLogin.AgentLoginResponse;

import static io.restassured.RestAssured.*;

public class TestCase extends BaseTestCase{

    @Test
    public void agentLogin(){
        String username = "admin@smma.id";
        String password = "admin";
        baseURI = baseUrl();
        basePath= "/v1/agent/login";
        AgentLoginRequest agentLoginRequest = AgentLoginRequest.agentLoginRequest(username, password);

        Response response = given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(agentLoginRequest)
                .post()
                .then()
                .log().all()
                .extract().response();

        ResponseBody body = response.getBody();
        AgentLoginResponse responseBody = body.as(AgentLoginResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertNotNull(responseBody.getAgentProfile().getAgentId());
        Assertions.assertNotNull(responseBody.getAgentProfile().getAccount().getAccountId());
    }
}
