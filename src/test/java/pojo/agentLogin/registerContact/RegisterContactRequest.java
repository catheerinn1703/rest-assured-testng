package pojo.agentLogin.registerContact;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class RegisterContactRequest {
    private String name;
    private String email;
    private String phone;

    public static RegisterContactRequest registerContactRequest(String name, String email, String phone) {
        return RegisterContactRequest.builder().name(name)
                .email(email)
                .phone(phone)
                .build();

    }

}
