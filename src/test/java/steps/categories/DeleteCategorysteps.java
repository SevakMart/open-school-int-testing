package steps.categories;

import io.cucumber.java.en.Then;
import manager.CategoryManager;
import org.assertj.core.api.Assertions;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;

public class DeleteCategorysteps {

    @Then("Get Subcategory from DB")
    public void getSubcategoryFromDB() {
        CategoryManager categoryIdManager = new CategoryManager();
        int categoryId = categoryIdManager.getSubCategoryId();
        SharedTestData.setCategoryId(categoryId);
    }

    @Then("Delete category by Id")
    public void deleteCategoryById() {
        RequestsUtils.delete("categories/" + SharedTestData.getSubCategoryId());
    }

    @Then("Validate message of response")
    public void validateMessageOfResponse() {
        String message = "Deleting the parent category is not allowed";
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message").equals(message));
    }

    @Then("Delete Parentcategory by Id")
    public void deleteParentcategoryById() {
        RequestsUtils.delete("categories/" + SharedTestData.getCategoryId());
    }
}
