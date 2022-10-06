package pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    private int id;
    private String name;
    private String surname;
    private String professionName;
    private int courseCount;
    private String userImgPath;
    private String roleType;
    private String company;

//    public static String getUser(int id, String name, String surname, String professionName, int courseCount, String userImgPath, String roleType, String company ){
//        return String.format("{\n" +
//                "  \"id\": \"%d\",\n" +
//                "  \"name\": \"%s\"\n" +
//                "  \"surname\": \"%s\"\n" +
//                "  \"professionName\": \"%s\"\n" +
//                "  \"courseCount\": \"%d\"\n" +
//                "  \"userImgPath\": \"%s\"\n" +
//                "  \"roleType\": \"%s\"\n" +
//                "  \"company\": \"%s\"\n" +
//                "}",id, name, surname, professionName, courseCount, userImgPath, roleType, company);
//    }
}