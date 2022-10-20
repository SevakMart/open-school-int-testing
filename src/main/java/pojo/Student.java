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