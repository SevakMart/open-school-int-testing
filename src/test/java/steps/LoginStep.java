package steps;

import io.cucumber.java.en.Then;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;
import io.cucumber.java.en.When;
import providers.LoginBodyProvider;

import java.io.IOException;

public class LoginStep {

    @When("Login by valid {string} password and {string} email")
    public void loginByValidPasswordAndEmail(String password, String email) {
        String body = LoginBodyProvider.getLoginBody(password, email);
        RequestsUtils.post("auth/login", body);
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
    }

    @Then("Validate login success response values")
    public void validateLoginSuccessResponseValues() {
        ResponseUtils.validateResponseAgainstJSONSchema("schema/getLoginRequestSchema.json");
    }
}
