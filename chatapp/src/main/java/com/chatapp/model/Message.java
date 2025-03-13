package com.chatapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String receiver;
    private String content;
    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "typing_status_id", referencedColumnName = "id", nullable = false)
    private TypingStatusRequest typingStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public TypingStatusRequest getTypingStatus() {
        return typingStatus;
    }

    public void setTypingStatus(TypingStatusRequest typingStatus) {
        this.typingStatus = typingStatus;
    }
}