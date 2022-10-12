package steps;

import business.RequestsUtils;
import io.cucumber.java.en.When;
import providers.LoginBodyProvider;

public class LoginStep {

    @When("Login by valid {} password and {} email")
    public void loginByValidTestPasswordAndEmail(String password, String email) {
        String body = LoginBodyProvider.getLoginBody(password, email);
        RequestsUtils.post("auth/login", body);
    }
}
