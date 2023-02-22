package pojo.agentLogin;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AgentLoginResponse {
    public String token;
    AgentProfile agentProfile;

    public class AgentProfile {
        public String agentId;
        public String firstName;
        public String lastName;
        public String email;
        public String phone;
        public Boolean active ;
        public Account account ;
        public String role;
        public String supervisor;
        public String manager;

        public class Account {
            public String accountId;
            public String accountName;
        }
    }
}
