package pl.chat.online_courses_chat.service;

import pl.chat.online_courses_chat.model.ChatMessage;

import java.util.List;

public interface ChatService {

    void setUserData(String username, String courseTitle);

    List<ChatMessage> getChatHistory(String courseTitle);

    ChatMessage saveChatMessage(ChatMessage chatMessage, String courseTitle);
}
