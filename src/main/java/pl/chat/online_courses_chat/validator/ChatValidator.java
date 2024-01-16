package pl.chat.online_courses_chat.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.chat.online_courses_chat.exception.CustomErrorException;
import pl.chat.online_courses_chat.exception.errors.ErrorCodes;

@Component
public class ChatValidator {

    public void validateChatTitleAndChatUser(String username, String courseTitle) {
        validateChatTitle(courseTitle);
        validateChatUser(username);
    }

    private void validateChatTitle(String courseTitle) {
        if (courseTitle == null || courseTitle.isBlank() || courseTitle.isEmpty()) {
            throw new CustomErrorException("courseTitle", ErrorCodes.FIELD_REQUIRED, HttpStatus.BAD_REQUEST);
        }

        if (courseTitle.length() < 3 || courseTitle.length() > 30) {
            throw new CustomErrorException("courseTitle", ErrorCodes.WRONG_FIELD_SIZE, HttpStatus.BAD_REQUEST);
        }
    }

    private void validateChatUser(String username) {
        if (username == null || username.isBlank() || username.isEmpty()) {
            throw new CustomErrorException("courseTitle", ErrorCodes.FIELD_REQUIRED, HttpStatus.BAD_REQUEST);
        }

        if (username.length() < 3 || username.length() > 20) {
            throw new CustomErrorException("username", ErrorCodes.WRONG_FIELD_SIZE, HttpStatus.BAD_REQUEST);
        }
    }
}
