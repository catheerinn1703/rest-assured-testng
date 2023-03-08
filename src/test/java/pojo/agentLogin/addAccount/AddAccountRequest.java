package pojo.agentLogin.addAccount;

import lombok.Builder;
import  lombok.Data;

@Builder
@Data
public class AddAccountRequest {
    private String name;
    private String description;
    private String countryCode;

    public static AddAccountRequest addAccountRequest(String name, String description, String countryCode){
        return AddAccountRequest.builder().name(name)
                .description(description)
                .countryCode(countryCode)
                .build();
    }


}
