package providers.dataProviders;

import java.util.List;

public final class SharedTestData {

    private static String token = "";

    private static String resetPasswordToken;

    private static int userId;

    private static int categoryId;

    private static int subCategoryId;

    private static List<?> usersSavedMentorsList;

    private static int courseId;

    private static int faqsId;

    private static String categorytitle;

    private static int enrolledCourseId;

    private static int questionIdToThePeers;

    private static int answerIdToThePeers;

    public static int getEnrolledCourseId() {
        return enrolledCourseId;
    }

    public static void setEnrolledCourseId(int enrolledCourseId) {
        SharedTestData.enrolledCourseId = enrolledCourseId;
    }

    public static int getQuestionIdToThePeers() {
        return questionIdToThePeers;
    }

    public static void setQuestionIdToThePeers(int questionIdToThePeers) {
        SharedTestData.questionIdToThePeers = questionIdToThePeers;
    }

    public static int getSubCategoryId() {
        return subCategoryId;
    }

    public static List<?> getUsersSavedMentorsList() {
        return usersSavedMentorsList;
    }

    public static void setUsersSavedMentorsList(List<?> usersSavedMentorsList) {
        SharedTestData.usersSavedMentorsList = usersSavedMentorsList;
    }

    public static void setSubCategoryId(int subCategoryId) {
        SharedTestData.subCategoryId = subCategoryId;
    }

    public static void setCategoryId(int categoryId) {
        SharedTestData.categoryId = categoryId;
    }

    public static int getCategoryId() {
        return categoryId;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SharedTestData.token = token;
    }

    public static String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public static void setResetPasswordToken(String resetPasswordToken) {
        SharedTestData.resetPasswordToken = resetPasswordToken;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        SharedTestData.userId = userId;
    }

    public static int getFaqsId() {
        return faqsId;
    }

    public static void setFaqsId(int faqsId) {
        SharedTestData.faqsId = faqsId;
    }

    public static int getCourseId() {
        return courseId;
    }

    public static void setCourseId(int courseId) {
        SharedTestData.courseId = courseId;
    }

    public static String getCategorytitle() {
        return categorytitle;
    }

    public static void setCategorytitle(String categorytitle) {
        SharedTestData.categorytitle = categorytitle;
    }

    public static int getAnswerIdToThePeers() {
        return answerIdToThePeers;
    }

    public static void setAnswerIdToThePeers(int answerIdToThePeers) {
        SharedTestData.answerIdToThePeers = answerIdToThePeers;
    }
}
