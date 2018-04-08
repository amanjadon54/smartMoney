import oracle.adf.view.rich.component.rich.input.RichInputText;

public class PasswordResetBeanNew {
    private RichInputText userName;
    private RichInputText securityQuestion;

    public PasswordResetBeanNew() {
    }

    public void setUserName(RichInputText userName) {
        this.userName = userName;
    }

    public RichInputText getUserName() {
        return userName;
    }

    public void setSecurityQuestion(RichInputText securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public RichInputText getSecurityQuestion() {
        return securityQuestion;
    }


}
