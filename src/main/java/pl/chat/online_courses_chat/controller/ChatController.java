package pl.chat.online_courses_chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.chat.online_courses_chat.model.ChatMessage;
import pl.chat.online_courses_chat.service.ChatService;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public String home(@RequestParam String username, @RequestParam String courseTitle, Model model) {
        chatService.setUserData(username, courseTitle);
        model.addAttribute("history", chatService.getChatHistory(courseTitle));
        return "index.html";
    }

    @MessageMapping("/chat/addUser/{courseTitle}")
    @SendTo("/channel/{courseTitle}")
    public ChatMessage addUser(@DestinationVariable String courseTitle, @Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatService.saveChatMessage(chatMessage, courseTitle);
    }

    @MessageMapping("/chat/sendMessage/{courseTitle}")
    @SendTo("/channel/{courseTitle}")
    public ChatMessage sendMessage(@DestinationVariable String courseTitle, @Payload ChatMessage chatMessage) {
        return chatService.saveChatMessage(chatMessage, courseTitle);
    }
}
