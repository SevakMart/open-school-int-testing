package providers.dataProviders;

import pojo.Student;
import utils.PasswordUtils;

public class StudentProvider {
    public static Student getRandomStudent() {
        String firstName = String.valueOf(System.currentTimeMillis());
        String lastName = firstName + "yan";
        String email = firstName + "@gmail.com";
        String password = PasswordUtils.generateStrongPassword();
        return new Student(firstName, lastName, email, password);
    }
}
