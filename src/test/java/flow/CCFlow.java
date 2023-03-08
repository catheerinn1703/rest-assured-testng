package flow;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.agentLogin.agentLogin.AgentLoginRequest;
import pojo.agentLogin.addAccount.AddAccountRequest;
import pojo.agentLogin.agentLogin.AgentLoginResponse;
import pojo.agentLogin.registerContact.RegisterContactRequest;
import pojo.agentLogin.setPassword.SetPasswordRequest;
import testData.TestData;

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

    public Response createInbox(String accountId, String token) {
        baseURI = baseUrl();
        RestAssured.basePath = "/v1/account/{aid}/web-widget/inboxes";
        File body = new File(System.getProperty("user.dir") + "/src/test/resources/agentLogin.json");

        Response response = given().log().all()
                .pathParam("aid", accountId)
                .header("Authorization", "jwt " + token)
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .post()
                .then()
                .log().all()
                .extract().response();

        return response;
    }

   public Response addAccount(String name, String description, String countryCode, String token){
        baseURI = baseUrl();
        RestAssured.basePath = "/v1/account";

        AddAccountRequest body = AddAccountRequest.addAccountRequest(name,description,countryCode);

        Response response = given().log().all()
                .header("Authorization", "jwt " + token)
                .header("Content-type","application/json")
                .and()
                .body(body)
                .post()
                .then()
                .log().all()
                .extract().response();
        return response;
    }

    public Response setPassword(String username, String password, String token) {
        baseURI = baseUrl();
        RestAssured.basePath = "/v1/agent/password";

        SetPasswordRequest body = SetPasswordRequest.setPasswordRequest(username, password);
        Response response = given().log().all()
                .header("Authorization", "jwt " + token)
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .post()
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response addInboxMember(String accountId, String token, String inboxId, String agentId) {
        baseURI = baseUrl();
        RestAssured.basePath = "v1/account/{accountId}/inbox/{inboxId}/member?agentId";

        Response response = given().log().all()
                .pathParam("accountId", accountId)
                .pathParam("inboxId", inboxId)
                .queryParam("agentId", agentId)
                .header("Authorization", "jwt " + token)
                .header("Content-type", "application/json")
                .and()
                .post()
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response removeInboxMember(String accountId, String token, String inboxId, String agentId) {
        baseURI = baseUrl();
        RestAssured.basePath = "v1/account/{accountId}/inbox/{inboxId}/member?agentId";

        Response response = given().log().all()
                .pathParam("accountId", accountId)
                .pathParam("inboxId", inboxId)
                .queryParam("agentId", agentId)
                .header("Authorization", "jwt " + token)
                .header("Content-type", "application/json")
                .and()
                .delete()
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response registerContact(String websiteToken, String name, String email, String phone) {
        baseURI = baseUrl();
        RestAssured.basePath = "/v1/widget/website-token/{websiteToken}/auth";
        RegisterContactRequest body = RegisterContactRequest.registerContactRequest(name, email, phone);

        return given().log().all()
                .pathParam("websiteToken", websiteToken)
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .post()
                .then()
                .log().all()
                .extract().response();
    }
   }




