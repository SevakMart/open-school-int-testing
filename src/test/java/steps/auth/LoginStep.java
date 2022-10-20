package steps.auth;

import org.assertj.core.api.Assertions;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pojo.Student;
import providers.LoginBodyProvider;

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

    @When("Login by invalid {string} password and {string} email")
    public void loginByInvalidPasswordAndEmail(String password, String email) {
        String body = LoginBodyProvider.getLoginBody(password, email);
        RequestsUtils.post("auth/login", body);
    }

    @Then("Validate login error response values")
    public void validateLoginErrorResponseValues() {
        ResponseUtils.validateResponseAgainstJSONSchema("schema/getLoginErrorSchema.json");
    }

    @Then("Verify login error message")
    public void verifyLoginErrorMessage() {
        String errorMessage =  ResponseUtils.getStringFromResponse("message");
        Assertions.assertThat(errorMessage.contains("email"));
    }
}
