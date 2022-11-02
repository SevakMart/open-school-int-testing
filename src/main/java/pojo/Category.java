package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.messages.internal.com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("logoPath")
    private String logoPath;
}
