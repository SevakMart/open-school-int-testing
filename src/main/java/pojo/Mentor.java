package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mentor {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("professionName")
    private String professionName;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("courseCount")
    private int courseCount;
    @JsonProperty("userImgPath")
    private String userImgPath;
    @JsonProperty("emailPath")
    private String emailPath;
    @JsonProperty("linkedinPath")
    private String linkedinPath;
}
