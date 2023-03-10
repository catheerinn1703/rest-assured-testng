package pojo.agentLogin.onboardAgent;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OnboardAgentResponse {
    private String agentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean active;
    private String employeeId;
    private Account account;
    private String role;
    private Object supervisor;
    private String manager;

    public static class Account {
    public String accountId;
    public String accountName;
    }

}