package steps.categories;

import io.cucumber.java.en.Then;
import manager.CategoryIdManager;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;

import java.sql.SQLException;

public class CategoriesById {

    @Then("Get category from DB")
    public void getCategoryFromDB() throws SQLException {
        CategoryIdManager categoryIdManager = new CategoryIdManager();
        int categoryId = categoryIdManager.getCategoryId();
        SharedTestData.setCategoryId(categoryId);
    }

    @Then("Get Subcategory from DB")
    public void getSubcategoryFromDB() throws SQLException {
        CategoryIdManager categoryIdManager = new CategoryIdManager();
        int categoryId = categoryIdManager.getSubCategoryId();
        SharedTestData.setCategoryId(categoryId);
    }

    @Then("Find category or subcategory by id")
    public void findCategoryOrSubcategoryById() {
        int categoryId = SharedTestData.getCategoryId();
        RequestsUtils.getById("categories/{id}", SharedTestData.getToken(), categoryId);
    }

    @Then("Validate categoryid success response values")
    public void validateCategoryidSuccessResponseValues() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getCategoryByIdRequest.json");
    }
}
