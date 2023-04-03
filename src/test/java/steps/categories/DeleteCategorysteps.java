package steps.categories;

import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;
import providers.dataProviders.SharedTestData;

public class DeleteCategorysteps {

    @Then("Delete category by Id")
    public void deleteCategoryById() {
        RequestsUtils.delete("categories/" + SharedTestData.getSubCategoryId());
    }

    @Then("Validate message of response")
    public void validateMessageOfResponse() {
        String message = "Deleting the parent category is not allowed";
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message")).isEqualTo(message);
    }

    @Then("Calling delete Parentcategory by Id")
    public void callingDeleteParentcategoryById() {
        RequestsUtils.delete("categories/" + SharedTestData.getCategoryId());
    }
}
