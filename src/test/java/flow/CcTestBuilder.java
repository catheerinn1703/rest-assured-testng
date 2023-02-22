package flow;

import pojo.agentLogin.AgentLoginRequest;

public class CcTestBuilder {

    public AgentLoginRequest setAgentLoginRequest(String username, String password){
        AgentLoginRequest request = AgentLoginRequest.agentLoginRequest(username, password);
        return request;
    }
}
