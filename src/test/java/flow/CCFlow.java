package flow;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.agentLogin.agentLogin.AgentLoginRequest;
import pojo.agentLogin.createInbox.CreateInboxResponse;

import java.io.File;

import static io.restassured.RestAssured.*;

public class CCFlow extends BaseUrl {

    public Response agentDoLogin(String username, String password) {
        baseURI = baseUrl();
        RestAssured.basePath = "/v1/agent/login";
        AgentLoginRequest agentLoginRequest = AgentLoginRequest.agentLoginRequest(username, password);

        Response response = given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(agentLoginRequest)
                .post()
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response createInbox(String accountId, String token){
        baseURI = baseUrl();
        RestAssured.basePath = "/v1/account/{aid}/web-widget/inboxes";
        File body = new File(System.getProperty("user.dir")+"/src/test/resources/agentLogin.json");

        Response response = given().log().all()
                .pathParam("aid",accountId)
                .header("Authorization","jwt "+token)
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .post()
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
