package steps.auth;

import providers.dataProviders.TestDataProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manager.AuthManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import providers.bodyProviders.EmailBodyProvider;
import providers.bodyProviders.ResetBodyProvider;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;
import providers.dataProviders.SharedTestData;


public class PasswordStep {
    @Given("Request of password forget with provided email")
    public void requestOfPasswordForgetWithProvidedEmail() {
        String body = EmailBodyProvider.getEmailBody(TestDataProvider.getPropertyValue("userEmail"));
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
        String body = ResetBodyProvider.getResetBody(SharedTestData.getResetPasswordToken(), TestDataProvider.getPropertyValue("userPsd"));
        RequestsUtils.post("auth/password/reset", body);
    }

    @And("Verify reset password success message")
    public void verifyResetPasswordSuccessMessage() {
        String msg = ResponseUtils.getStringFromResponse("message");
        Assertions.assertThat(msg).contains("Your password has been successfully changed");
    }

    @Given("Request of password forget with provided invalid email")
    public void requestOfPasswordForgetWithProvidedInvalidEmail() {
        String body = EmailBodyProvider.getEmailBody(RandomStringUtils.randomAlphabetic(5) + "@gmail.com");
        RequestsUtils.post("auth/password/forgot", body);
    }
}
