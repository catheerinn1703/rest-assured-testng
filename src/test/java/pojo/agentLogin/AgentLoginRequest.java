package pojo.agentLogin;


import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class AgentLoginRequest {
    private String username;
    private String password;

    public static AgentLoginRequest agentLoginRequest(String username, String password) {
        return AgentLoginRequest.builder().username(username)
                .password(password)
                .build();
    }
}
