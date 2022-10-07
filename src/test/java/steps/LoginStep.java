package steps;

import business.RequestsUtils;
import business.ResponseUtils;
import business.SharedData;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pojo.User;
import providers.LoginBodyProvider;

public class LoginStep {
    @When("Login by valid {} password and {} email")
    public void loginByValidTestPasswordAndEmail(String password, String email) {
        String body= LoginBodyProvider.getLoginBody(password, email);
        RequestsUtils.post("auth/login", body);
    }
}