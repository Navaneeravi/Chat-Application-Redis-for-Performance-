package com.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chatapp.model.TypingStatusRequest;
import com.chatapp.service.StatusService;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping("/{user}/typing")
    public void setTypingStatus(@PathVariable String user, @RequestBody TypingStatusRequest request) {
        statusService.updateTypingStatus(user, request.isTyping());
    }

    @GetMapping("/{user}/typing")
    public boolean getTypingStatus(@PathVariable String user) {
        return statusService.getTypingStatus(user);
    }
}
