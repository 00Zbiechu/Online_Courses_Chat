package pl.chat.online_courses_chat.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.chat.online_courses_chat.model.ChatMessage;
import pl.chat.online_courses_chat.repository.ChatMessageRepository;
import pl.chat.online_courses_chat.validator.ChatValidator;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatValidator chatValidator;

    private final ChatMessageRepository chatMessageRepository;

    private final HttpSession session;

    @Override
    public void setUserData(String username, String courseTitle) {
        chatValidator.validateChatTitleAndChatUser(username, courseTitle);
        session.setAttribute("username", username);
        session.setAttribute("courseTitle", courseTitle);
    }

    @Override
    public List<ChatMessage> getChatHistory(String courseTitle) {
        return chatMessageRepository.findByCourseTitleOrderBySendTimeAsc(courseTitle);
    }

    @Override
    public ChatMessage saveChatMessage(ChatMessage chatMessage, String courseTitle) {
        chatMessage.setCourseTitle(courseTitle);
        chatMessage.setSendTime(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }
}
