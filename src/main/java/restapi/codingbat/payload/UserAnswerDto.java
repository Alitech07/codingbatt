package restapi.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDto {
    private Integer userId;
    private Integer problemId;
    private String answer;
}
