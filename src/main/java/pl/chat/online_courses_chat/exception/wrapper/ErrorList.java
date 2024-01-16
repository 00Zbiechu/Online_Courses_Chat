package pl.chat.online_courses_chat.exception.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.chat.online_courses_chat.exception.model.ErrorDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorList {

    private List<ErrorDTO> errorList;
}
