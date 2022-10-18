package steps.auth;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.assertj.core.api.Assertions;
import providers.EmailBodyProvider;
import utils.RequestsUtils;
import utils.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

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
}
