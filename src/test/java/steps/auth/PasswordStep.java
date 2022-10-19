package steps.auth;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manager.PasswordTokenManager;
import org.assertj.core.api.Assertions;
import providers.EmailBodyProvider;
import providers.ResetBodyProvider;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;

public class PasswordStep {
    @Given("Request of password forget with {string} email")
    public void requestOfPasswordForgetWithEmail(String email) {
        String body = EmailBodyProvider.getEmailBody(email);
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

    @Given("Get reset password token by {string} email")
    public void getResetPasswordTokenByEmail(String email) {
        PasswordTokenManager manager = new PasswordTokenManager();
        String resetPassToken = manager.getPasswordToken(email);
        SharedTestData.setResetPasswordToken(resetPassToken);
    }

    @Then("Make request with token, new password and confirmed password {string}")
    public void makeRequestWithTokenNewPasswordAndConfirmedPassword(String password) {
        String body = ResetBodyProvider.getResetBody(SharedTestData.getResetPasswordToken(), password);
        RequestsUtils.post("auth/password/reset", body);
    }

    @And("Verify reset password success message")
    public void verifyResetPasswordSuccessMessage() {
        String msg = ResponseUtils.getStringFromResponse("message");
        Assertions.assertThat(msg).contains("Your password has been successfully changed");
    }
}
