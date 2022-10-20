package steps.auth;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pojo.Student;
import providers.StudentProvider;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;

public class AccountVerificationStep {
    @Given("Prepare user for account verification")
    public void prepareUserForAccountVerification() {
        Student student = StudentProvider.getRandomStudent();
        RequestsUtils.post("auth/register", student);
        SharedTestData.setUserId(ResponseUtils.getIntFromResponse("userId"));
    }

    @Then("Resend email for account verification")
    public void resendEmailForAccountVerification() {
        int userId = SharedTestData.getUserId();
        RequestsUtils.get("auth/" + userId + "/account/verification");
    }

    @And("Verify account verification success status code")
    public void verifyAccountVerificationSuccessStatusCode() {
        Assertions.assertThat(ResponseUtils.getStatusCodeFromResponse()).isEqualTo(HttpStatus.SC_OK);
    }

    @Given("Invalid user {} requests for account verification")
    public void invalidUserRequestsForAccountVerification(String userId) {
        RequestsUtils.get("auth/" + userId + "/account/verification");
    }

    @And("Verify account verification error status code")
    public void verifyAccountVerificationErrorStatusCode() {
        Assertions.assertThat(ResponseUtils.getStatusCodeFromResponse()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }
}
