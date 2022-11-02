package steps.auth;

import providers.dataProviders.TestDataProvider;
import org.assertj.core.api.Assertions;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;
import providers.dataProviders.SharedTestData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import providers.bodyProviders.LoginBodyProvider;

public class LoginStep {

    @When("Login by valid {string} email and {string} password")
    public void loginByValidEmailAndPassword(String email, String password) {
        String body = LoginBodyProvider.getLoginBody(TestDataProvider.getPropertyValue(password), TestDataProvider.getPropertyValue(email));
        RequestsUtils.post("auth/login", body);
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
    }

    @Then("Validate login success response values")
    public void validateLoginSuccessResponseValues() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/authSchemas/getLoginRequestSchema.json");
    }

    @When("Login by invalid {string} password and {string} email")
    public void loginByInvalidPasswordAndEmail(String password, String email) {
        String body = LoginBodyProvider.getLoginBody(password, email);
        RequestsUtils.post("auth/login", body);
    }

    @Then("Validate login error response values")
    public void validateLoginErrorResponseValues() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/authSchemas/getLoginErrorSchema.json");
    }

    @Then("Verify login error message")
    public void verifyLoginErrorMessage() {
        String errorMessage = ResponseUtils.getStringFromResponse("message");
        Assertions.assertThat(errorMessage.contains("email"));
    }
}
