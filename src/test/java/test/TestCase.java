package test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import pojo.agentLogin.addAccount.AddAccountResponse;
import pojo.agentLogin.agentLogin.AgentLoginResponse;
import pojo.agentLogin.createInbox.CreateInboxResponse;
import pojo.agentLogin.onboardAgent.OnboardAgentResponse;
import pojo.agentLogin.registerContact.RegisterContactResponse;
import testData.TestData;

@org.testng.annotations.Test
public class TestCase extends BaseTestCase {
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
        Response response = ccFlow.agentDoLogin(wrongUsername, password);
        AgentLoginResponse responseBody = response.getBody().as(AgentLoginResponse.class);

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals(responseBody.getMessage(), "agent not found");
    }

    @Test
    public void agentLoginWithIncorrectPassword() {
        String wrongPassword = "abc";
        Response response = ccFlow.agentDoLogin(wrongPassword, password);
        AgentLoginResponse responseBody = response.getBody().as(AgentLoginResponse.class);

        Assertions.assertEquals(401, response.statusCode());
        Assertions.assertEquals(responseBody.getMessage(), "Wrong password, try again");
    }

    @Test(enabled = false)
    public void createInboxSuccessfully() {
        Response response = ccFlow.agentDoLogin(username, password);

        AgentLoginResponse responseBody = response.as(AgentLoginResponse.class);
        String token = responseBody.getToken();

        Response createInboxResponse = ccFlow.createInbox(TestData.ACCOUNT_ID, getAdminToken());
        CreateInboxResponse createInboxBody = createInboxResponse.getBody().as(CreateInboxResponse.class);

        Assertions.assertEquals(201, createInboxResponse.statusCode());
        Assertions.assertEquals("Inbox Created Successfully", createInboxBody.getMessage());
        Assertions.assertNotNull(createInboxBody.getInboxId());
    }

    @Test
    public void setPasswordSuccessfully() {
        Response setPasswordResponse = ccFlow.setPassword(TestData.USERNAME_SET_PASSWORD,
                TestData.PASSWORD_SET_PASSWORD, getAdminToken());

        Assertions.assertEquals(200, setPasswordResponse.statusCode());
    }

    @Test
    public void addInboxMemberSuccessfully() {
        Response addInboxMemberResponse = ccFlow.addInboxMember(TestData.ACCOUNT_ID, getAdminToken(),
                TestData.INBOX_ID, TestData.AGENT_ID);

        ccFlow.removeInboxMember(TestData.ACCOUNT_ID, getAdminToken(),
                TestData.INBOX_ID, TestData.AGENT_ID);

        Assertions.assertEquals(200, addInboxMemberResponse.statusCode());
    }

    @Test
    public void registerContactSuccessfully() {
        Response response = ccFlow.registerContact(TestData.WEBSITE_TOKEN, TestData.NAME_CONTACT,
                TestData.EMAIL_CONTACT, TestData.PHONE_CONTACT);
        RegisterContactResponse registerContactBody = response.getBody().as(RegisterContactResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(registerContactBody.getName(), TestData.NAME_CONTACT);
        Assertions.assertNotNull(registerContactBody.getEmail(), TestData.EMAIL_CONTACT);
    }

    @Test
    public void registerContactFailedWithIncorrectWebsiteToken() {
        Response response = ccFlow.registerContact(TestData.INVALID_WEBSITE_TOKEN, TestData.NAME_CONTACT,
                TestData.EMAIL_CONTACT, TestData.PHONE_CONTACT);
        RegisterContactResponse registerContactBody = response.getBody().as(RegisterContactResponse.class);

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Wrong website token", registerContactBody.getMessage());
    }

    @Test
    public void addAccountSuccessfully() {
        Response addAccountResponse = ccFlow.addAccount(TestData.NAME_ADD_ACCOUNT,
                TestData.DESCRIPTION_ADD_ACCOUNT, TestData.COUNTRY_CODE_ADD_ACCOUNT, getAdminToken());

        AddAccountResponse addAccountResponseBody = addAccountResponse.getBody()
                .as(AddAccountResponse.class);
        Assertions.assertEquals(200, addAccountResponse.statusCode());
        Assertions.assertNotNull(addAccountResponseBody.accountId);
        Assertions.assertEquals(addAccountResponseBody.getName(), TestData.NAME_ADD_ACCOUNT);
        Assertions.assertEquals(addAccountResponseBody.getCountryCode(), TestData.COUNTRY_CODE_ADD_ACCOUNT);
    }

    @Test
    public void onboardAgentSuccessfully() {
        Response response = ccFlow.onboardAgent(getAdminToken());
        OnboardAgentResponse responseBody = response.as(OnboardAgentResponse.class);

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertNotNull(responseBody.getAgentId());
        Assertions.assertNotNull(responseBody.getAccount().accountId);
    }
}





