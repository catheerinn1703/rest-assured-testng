package pojo.agentLogin.createInbox;

import pojo.agentLogin.agentLogin.AgentLoginRequest;

public class CreateInboxRequest {
    public void setInboxName(String inboxName) {
        this.inboxName = inboxName;
    }

    public void setWebsiteUrl(String websiteUrl) {this.websiteUrl = websiteUrl;}

    public void setGreetingInfo(GreetingInfo greetingInfo) {this.greetingInfo = greetingInfo;}

    public void setWidgetColor(String widgetColor) {this.widgetColor = widgetColor;}

    public void setWelcomeTitle(String welcomeTitle) {this.welcomeTitle = welcomeTitle;}

    public String inboxName;
    public String websiteUrl;
    public GreetingInfo greetingInfo;
    public String widgetColor;
    public String welcomeTitle;

    public class GreetingInfo {
        public void setGreetingEnabled(boolean greetingEnabled) {this.greetingEnabled = greetingEnabled;}

        public void setGreetingMessage(String greetingMessage) {this.greetingMessage = greetingMessage;}

        public boolean greetingEnabled;
        public String greetingMessage;
    }

    public static AgentLoginRequest agentLoginRequest(String username, String password) {
        return AgentLoginRequest.builder().username(username)
                .password(password)
                .build();
    }
}
