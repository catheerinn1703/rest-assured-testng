package pojo.agentLogin.registerContact;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class RegisterContactResponse {
    public String name;
    public String email;
    public String token;
    public String message;
    public int status;

}