package test;

import flow.CCFlow;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.agentLogin.agentLogin.AgentLoginResponse;
import testData.TestData;

public class BaseTestCase {
    CCFlow ccFlow = new CCFlow();

    @BeforeTest
    public void logging() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public String getAdminToken() {
        Response response = ccFlow.agentDoLogin(TestData.ADMIN_USERNAME, TestData.ADMIN_PASSWORD);
        AgentLoginResponse responseBody = response.getBody().as(AgentLoginResponse.class);
        String token = responseBody.getToken();

        return token;
    }
}
