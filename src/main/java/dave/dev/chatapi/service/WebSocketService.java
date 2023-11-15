package dave.dev.chatapi.service;

import dave.dev.chatapi.model.Message;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(Message message) {
        messagingTemplate.convertAndSend("/topic/chat", message);
    }
}
