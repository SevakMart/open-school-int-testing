package steps.categories;

import manager.CategoryManager;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

public class GetCategorySteps {

    @Then("Get all categories and relevant subcategories")
    public void getAllCategoriesAndRelevantSubcategories() {
        RequestsUtils.get("categories", SharedTestData.getToken());
    }

    @Then("Set Admin Token")
    public void setAdminToken() {
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
    }

    @Then("Get any category title from DB")
    public void getCategoryTitleFromDB() {
        CategoryManager categoryTitleManager = new CategoryManager();
        String categoryTitle = categoryTitleManager.getCategoryByTitle();
        SharedTestData.setCategoryTitle(categoryTitle);
    }

    @Then("Get category by title")
    public void findAllCategoriesByTitle() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("title", SharedTestData.getCategoryTitle());
        RequestsUtils.getByQueryParams("categories/subcategories", queryParams);
    }

    @When("Get all categories and relevant subcategories without login")
    public void getAllCategoriesAndRelevantSubcategoriesWithoutLogin() {
        RequestsUtils.get("categories");
    }

    @Then("Get all parent categories")
    public void getAllParentCategories() {
        RequestsUtils.get("parentCategories");
    }

    @Then("Get category from DB")
    public void getCategoryFromDB() {
        CategoryManager categoryIdManager = new CategoryManager();
        int categoryId = categoryIdManager.getParentCategoryId();
        SharedTestData.setCategoryId(categoryId);
    }

    @Then("Find category or subcategory by id")
    public void findCategoryOrSubcategoryById() {
        int categoryId = SharedTestData.getCategoryId();
        RequestsUtils.get("categories/" + categoryId, SharedTestData.getToken());
    }

    @Then("Validate categoryid success response values")
    public void validateCategoryidSuccessResponseValues() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getCategoryByIdRequest.json");
    }
}
