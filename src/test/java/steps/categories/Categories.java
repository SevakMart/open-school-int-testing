package steps.categories;

import manager.CategoryTitleManager;
import org.assertj.core.api.Assertions;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;

public class Categories {

    @Then("Get all categories and relevant subcategories")
    public void getAllCategoriesAndRelevantSubcategories() {
        RequestsUtils.get("categories", SharedTestData.getToken());
    }

    @Then("Set Admin Token")
    public void setAdminToken() {
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
    }

    @Then("Get any category title from DB")
    public void getCategoryTitleFromDB() throws SQLException {
        CategoryTitleManager categoryTitleManager = new CategoryTitleManager();
        String categoryTitle = categoryTitleManager.getCategoryByTitle();
        SharedTestData.setCategoryTitle(categoryTitle);
    }

    @Then("Get category by title")
    public void findAllCategoriesByTitle() {
        String title = SharedTestData.getCategoryTitle();
        RequestsUtils.getCategoryBySubCategoryTitle("categories/subcategories", SharedTestData.getToken(), title);
    }

    @When("Get all categories and relevant subcategories without login")
    public void getAllCategoriesAndRelevantSubcategoriesWithoutLogin() {
        RequestsUtils.get("categories");
    }

    @Then("Delete category by Id")
    public void deleteCategoryById() {
        RequestsUtils.deleteById("categories/{id}", SharedTestData.getToken(), SharedTestData.getCategoryId());
    }

    @Then("Validate message of response")
    public void validateMessageOfResponse() {
        String message = "Deleting the parent category is not allowed";
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message").equals(message));
    }

    @Then("Get all parent categories")
    public void getAllParentCategories() {
        RequestsUtils.get("parentCategories", SharedTestData.getToken());
    }
}
