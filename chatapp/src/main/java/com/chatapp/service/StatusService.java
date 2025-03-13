package com.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.model.TypingStatusRequest;
import com.chatapp.repository.TypingStatusRepository;

@Service
public class StatusService {

    @Autowired
    private TypingStatusRepository typingStatusRepository;

    public boolean getTypingStatus(String username) {
        TypingStatusRequest status = typingStatusRepository.findBySender(username);
        return status != null && status.isTyping();
    }

    public void updateTypingStatus(String username, boolean isTyping) {
        TypingStatusRequest status = typingStatusRepository.findBySender(username);

        if (status == null) {
            status = new TypingStatusRequest();
            status.setSender(username);
        }

        status.setTyping(isTyping);
        typingStatusRepository.save(status);
    }
}