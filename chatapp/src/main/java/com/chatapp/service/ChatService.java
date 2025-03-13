package com.chatapp.service;

import com.chatapp.model.Message;
import com.chatapp.model.TypingStatusRequest;
import com.chatapp.repository.MessageRepository;
import com.chatapp.repository.TypingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ChatService {

    private static final String MESSAGE_CACHE = "messages";

    @Autowired
    private RedisTemplate<String, Message> redisTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TypingStatusRepository typingStatusRepository;

    
    public void saveMessage(String chatRoom, Message message) {
        TypingStatusRequest typingStatus = typingStatusRepository.findBySender(message.getSender());
        if (typingStatus == null) {
            typingStatus = new TypingStatusRequest();
            typingStatus.setSender(message.getSender());
            typingStatus.setTyping(false); // Default typing status
            typingStatusRepository.save(typingStatus);
        }

        
        message.setTypingStatus(typingStatus);


        messageRepository.save(message);

        
        redisTemplate.opsForList().leftPush(MESSAGE_CACHE + ":" + chatRoom, message);
        redisTemplate.expire(MESSAGE_CACHE + ":" + chatRoom, 1, TimeUnit.HOURS);
    }

    public List<Message> getRecentMessages(String chatRoom) {
        return redisTemplate.opsForList().range(MESSAGE_CACHE + ":" + chatRoom, 0, 20);
    }

    public List<Message> getAllMessages(String receiver) {
        return messageRepository.findByReceiver(receiver);
    }
}