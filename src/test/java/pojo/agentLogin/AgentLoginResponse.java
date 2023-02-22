package pojo.agentLogin;

import lombok.Data;
import lombok.Getter;

@Data
public class AgentLoginResponse {
    public String getToken() {
        return token;
    }

    public AgentProfile getAgentProfile() {
        return agentProfile;
    }

    public String token;
    AgentProfile agentProfile;

    public class AgentProfile {
        public String agentId;
        public String firstName;
        public String lastName;
        public String email;
        public String phone;
        public Boolean active;
        public Boolean employeeId;
        public Account account;
        public String role;
        public String supervisor;
        public String manager;

        public class Account {
            public String accountId;
            public String accountName;

            public String getAccountId() {
                return accountId;
            }

            public String getAccountName() {
                return accountName;
            }
        }

        public String getAgentId() {
            return agentId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public Boolean getActive() {
            return active;
        }

        public Account getAccount() {
            return account;
        }

        public String getRole() {
            return role;
        }

        public String getSupervisor() {
            return supervisor;
        }

        public String getManager() {
            return manager;
        }
    }
}
