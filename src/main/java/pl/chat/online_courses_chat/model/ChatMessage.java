package pl.chat.online_courses_chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import pl.chat.online_courses_chat.type.MessageType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@RedisHash("ChatMessage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage implements Serializable {

    @Id
    private UUID id;

    private String content;

    private String sender;

    private MessageType messageType;

    @Indexed
    private String courseTitle;

    private LocalDateTime sendTime;
}
