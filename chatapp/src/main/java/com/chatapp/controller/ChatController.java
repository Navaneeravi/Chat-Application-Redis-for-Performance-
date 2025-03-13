package com.chatapp.controller;

import com.chatapp.model.Message;
import com.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    
    @PostMapping("/{chatRoom}/send")
    public String sendMessage(@PathVariable String chatRoom, @RequestBody Message message) {
        chatService.saveMessage(chatRoom, message);
        return "Message sent successfully!";
    }

    
    @GetMapping("/{chatRoom}/recent")
    public List<Message> getRecentMessages(@PathVariable String chatRoom) {
        return chatService.getRecentMessages(chatRoom);  // Updated to return List<Message>
    }

    
    @GetMapping("/{receiver}/all")
    public List<Message> getAllMessages(@PathVariable String receiver) {
        return chatService.getAllMessages(receiver);
    }
}