package steps.user;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import providers.dataProviders.Endpoints;
import providers.dataProviders.SharedTestData;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class GetUsersEnrolledCoursesSteps {

    private final Map<String, Object> pathVarForGet = new HashMap<>();
    @When("Find users enrolled courses by course status")
    public void findUsersEnrolledCoursesByCourseStatus() {
        int userId = SharedTestData.getUserId();
        pathVarForGet.put("userId",userId );
        RequestsUtils.get(Endpoints.FIND_USERS_ENROLLED_COURSES.url, SharedTestData.getToken(), pathVarForGet);
        SharedTestData.setEnrolledCourseId(ResponseUtils.getIntFromResponse("[0].id"));
    }
}
