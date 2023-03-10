package pojo.agentLogin.onboardAgent;

import lombok.Builder;
import lombok.Data;
import pojo.agentLogin.agentLogin.AgentLoginRequest;
import pojo.agentLogin.registerContact.RegisterContactRequest;

@Data
@Builder
public class OnboardAgentRequest {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String employeeId;
        private String role;
        private String manager;

        public static OnboardAgentRequest onboardAgentRequest
                (String firstName, String lastName, String email, String phone, String employeeId, String role,
                 String manager){
                return OnboardAgentRequest.builder() .firstName(firstName) .lastName(lastName)
                        .email(email)
                        .phone(phone) .employeeId(employeeId) .role(role) .manager(manager)
                        .build();

        }
}

