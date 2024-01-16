package pl.chat.online_courses_chat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.chat.online_courses_chat.model.ChatMessage;

import java.util.List;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, String> {

    List<ChatMessage> findByCourseTitleOrderBySendTimeAsc(String courseTitle);
}
