package business;

public final class SharedData {
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SharedData.token = token;
    }
}