package steps.auth;

import providers.bodyProviders.BodyProvider;
import providers.dataProviders.TestDataProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manager.AuthManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;
import providers.dataProviders.SharedTestData;

import java.util.HashMap;
import java.util.Map;


public class PasswordStep {
    private Map<String, Object> params = new HashMap<>();

    @Given("Request of password forget with provided email")
    public void requestOfPasswordForgetWithProvidedEmail() {
        params.put("email", TestDataProvider.getPropertyValue("userEmail"));
        String body = BodyProvider.getBody("email", params);
        RequestsUtils.post("auth/password/forgot", body);
    }

    @And("Verify forgot password success message")
    public void verifyForgotPasswordSuccessMessage() {
        String msg = ResponseUtils.getStringFromResponse("message");
        Assertions.assertThat(msg).isEqualTo("The verification code was sent to your email. Please check.");
    }

    @And("Verify forgot password error message")
    public void verifyForgotPasswordErrorMessage() {
        String msg = ResponseUtils.getStringFromResponse("message");
        Assertions.assertThat(msg).contains("email does not exist");
    }

    @Then("Get reset password token by provided email")
    public void getResetPasswordTokenByProvidedEmail() {
        AuthManager manager = new AuthManager();
        String resetPassToken = manager.getResetPasswordToken(TestDataProvider.getPropertyValue("userEmail"));
        SharedTestData.setResetPasswordToken(resetPassToken);
    }

    @Then("Make request with token, new password and confirmed password")
    public void makeRequestWithTokenNewPasswordAndConfirmedPassword() {
        params.put("token", SharedTestData.getResetPasswordToken());
        params.put("newPassword", TestDataProvider.getPropertyValue("userPsd"));
        params.put("confirmedPassword", TestDataProvider.getPropertyValue("userPsd"));
        String body = BodyProvider.getBody("reset", params);
        RequestsUtils.post("auth/password/reset", body);
    }

    @And("Verify reset password success message")
    public void verifyResetPasswordSuccessMessage() {
        String msg = ResponseUtils.getStringFromResponse("message");
        Assertions.assertThat(msg).contains("Your password has been successfully changed");
    }

    @Given("Request of password forget with provided invalid email")
    public void requestOfPasswordForgetWithProvidedInvalidEmail() {
        params.put("email", RandomStringUtils.randomAlphabetic(5) + "@gmail.com");
        String body = BodyProvider.getBody("email", params);
        RequestsUtils.post("auth/password/forgot", body);
    }
}
