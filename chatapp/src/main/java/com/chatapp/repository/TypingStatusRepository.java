package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chatapp.model.TypingStatusRequest;

public interface TypingStatusRepository extends JpaRepository<TypingStatusRequest, Long> {

    
    TypingStatusRequest findBySender(String sender);
}
