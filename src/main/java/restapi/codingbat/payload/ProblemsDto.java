package restapi.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemsDto {
    private String title;
    private String body;
    private String answer;
    private Integer categoryId;
}
