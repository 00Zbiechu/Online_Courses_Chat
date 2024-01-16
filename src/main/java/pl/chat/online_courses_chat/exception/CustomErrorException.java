package pl.chat.online_courses_chat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorException extends RuntimeException {

    private String field;

    private String errorCode;

    private HttpStatus httpStatus;
}
