package dave.dev.chatapi.controller;

import dave.dev.chatapi.model.Message;
import dave.dev.chatapi.service.MessageService;
import dave.dev.chatapi.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final MessageService messageService;
    private final WebSocketService webSocketService;

    @Autowired
    public ChatController(MessageService messageService, WebSocketService webSocketService) {
        this.messageService = messageService;
        this.webSocketService = webSocketService;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message sentMessage = messageService.saveMessage(message);
        webSocketService.sendMessage(sentMessage);
        return ResponseEntity.ok(sentMessage);
    }
}
