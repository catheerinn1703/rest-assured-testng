package pojo.agentLogin.addAccount;

public class AddAccountResponse {
    public String token;
    public String message;
    public  int status;
    public String accountId;
    public String name;
    public  String description;
    public  String countryCode;

    public String getToken(){return token;}

    public String getMessage(){return message;}

    public String getAccountId(){return accountId;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public String getCountryCode(){return countryCode;}

}
