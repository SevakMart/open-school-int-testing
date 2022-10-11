package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String psd;

    public static Student getRandomStudent(){
        String x = String.valueOf(System.currentTimeMillis());
        String firstName = x;
        String lastName = x + "yan";
        String email = x + "@gmail.com";
        String password = x + "Psd#";
        return new Student(firstName, lastName, email, password);
    }

    @Override
    public String toString() {
       return  "{\n" +
                "firstName : " + firstName + ",\n" +
                "lastName : " + lastName + ",\n" +
                "email : " + email +  ",\n" +
                "psd : " + psd + "\n" +
                "}";
    }
}