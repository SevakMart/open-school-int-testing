package pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Category {

    private int id;
    private String title;
    private String logoPath;
}
