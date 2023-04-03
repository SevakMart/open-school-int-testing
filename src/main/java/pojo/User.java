package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("professionName")
    private String professionName;
    @JsonProperty("courseCount")
    private int courseCount;
    @JsonProperty("userImgPath")
    private String userImgPath;
    @JsonProperty("roleType")
    private String roleType;
    @JsonProperty("company")
    private String company;
}
