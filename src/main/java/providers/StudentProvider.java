package providers;

import pojo.Student;
import utils.PasswordUtils;

public class StudentProvider {
    public static Student getRandomStudent(){
        String firstName =   String.valueOf(System.currentTimeMillis());
        String lastName = firstName + "yan";
        String email = firstName + "@gmail.com";
        String password = PasswordUtils.getPassword(8);
        return new Student(firstName, lastName, email, password);
    }
}
