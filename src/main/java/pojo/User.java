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
}
