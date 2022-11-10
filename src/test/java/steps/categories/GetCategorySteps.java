package steps.categories;

import providers.dataProviders.TestDataProvider;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;
import providers.dataProviders.SharedTestData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

public class GetCategorySteps {

    private Map<String, Object> params = new HashMap<>();
    @Then("Get all categories and relevant subcategories")
    public void getAllCategoriesAndRelevantSubcategories() {
        RequestsUtils.get("categories", SharedTestData.getToken());
    }

    @Then("Set Admin Token")
    public void setAdminToken() {
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
    }

    @Then("Get category by title")
    public void findAllCategoriesByTitle() {
        params.put("title", TestDataProvider.getPropertyValue("categoryTitle"));
        RequestsUtils.getByQueryParams("categories/subcategories", params);
    }

    @When("Get all categories and relevant subcategories without login")
    public void getAllCategoriesAndRelevantSubcategoriesWithoutLogin() {
        RequestsUtils.get("categories");
    }

    @Then("Get all parent categories")
    public void getAllParentCategories() {
        RequestsUtils.get("parentCategories");
    }

    @Then("Find category or subcategory by id")
    public void findCategoryOrSubcategoryById() {
        RequestsUtils.get("categories/" + SharedTestData.getCategoryId(), SharedTestData.getToken());
    }

    @Then("Validate categoryid success response values")
    public void validateCategoryidSuccessResponseValues() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getCategoryByIdRequest.json");
    }
}
