package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("rating")
    private double rating;
    @JsonProperty("difficulty")
    private String difficulty;
    @JsonProperty("keywords")
    private List<String> keywords;
}
