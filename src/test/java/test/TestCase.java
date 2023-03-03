package test;

import flow.CCFlow;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.agentLogin.agentLogin.AgentLoginRequest;
import pojo.agentLogin.agentLogin.AgentLoginResponse;
import pojo.agentLogin.createInbox.CreateInboxResponse;
import testData.TestData;

import static io.restassured.RestAssured.*;

public class TestCase extends BaseTestCase {

    CCFlow ccFlow = new CCFlow();
    String username = "admin@smma.id";
    String password = "admin";

    @Test
    public void agentLoginSuccessfully() {
        Response response = ccFlow.agentDoLogin(username, password);

        AgentLoginResponse responseBody = response.getBody().as(AgentLoginResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertNotNull(responseBody.getAgentProfile().getAgentId());
        Assertions.assertNotNull(responseBody.getAgentProfile().getAccount().getAccountId());
    }

    @Test
    public void agentLoginWithIncorrectUsername() {
        String wrongUsername = "abc";
        baseURI = baseUrl();
        basePath = "/v1/agent/login";
        AgentLoginRequest agentLoginRequest = AgentLoginRequest.agentLoginRequest(wrongUsername, password);

        Response response = given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(agentLoginRequest)
                .post()
                .then()
                .log().all()
                .extract().response();

        AgentLoginResponse responseBody = response.getBody().as(AgentLoginResponse.class);

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals(responseBody.getMessage(), "agent not found");
    }

    @Test
    public void agentLoginWithIncorrectPassword() {
        String wrongPassword = "abc";
        baseURI = baseUrl();
        basePath = "/v1/agent/login";
        AgentLoginRequest agentLoginRequest = AgentLoginRequest.agentLoginRequest(username, wrongPassword);

        Response response = given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(agentLoginRequest)
                .post()
                .then()
                .log().all()
                .extract().response();

        AgentLoginResponse responseBody = response.getBody().as(AgentLoginResponse.class);

        Assertions.assertEquals(401, response.statusCode());
        Assertions.assertEquals(responseBody.getMessage(), "Wrong password, try again");
    }

    @Test
    public void createInboxSuccessfully() {
        baseURI = baseUrl();
        basePath = "/v1/agent/login";
        Response response = ccFlow.agentDoLogin(username, password);

        ResponseBody body = response.getBody();
        AgentLoginResponse responseBody = body.as(AgentLoginResponse.class);
        String token = responseBody.getToken();

        Response createInboxResponse = ccFlow.createInbox(TestData.ACCOUNT_ID,token);
        CreateInboxResponse createInboxBody = createInboxResponse.getBody().as(CreateInboxResponse.class);

        Assertions.assertEquals(201, createInboxResponse.statusCode());
        Assertions.assertEquals("Inbox Created Successfully",createInboxBody.getMessage());
        Assertions.assertNotNull(createInboxBody.getInboxId());
    }

    @Test
    public void setPasswordSuccessfully() {
        Response response = ccFlow.agentDoLogin(username, password);

        ResponseBody body = response.getBody();
        AgentLoginResponse responseBody = body.as(AgentLoginResponse.class);
        String token = responseBody.getToken();

        Response setPasswordResponse = ccFlow.setPassword(TestData.USERNAME_SET_PASSWORD,
                TestData.PASSWORD_SET_PASSWORD,token);

        Assertions.assertEquals(200, setPasswordResponse.statusCode());
    }

}
