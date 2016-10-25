package demo.vonex.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.util.ObjectUtils;

/**
 * @author Jitendra Singh.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AskResponse {

    private String answer;
    private String error;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return !ObjectUtils.isEmpty(answer);
    }
}
