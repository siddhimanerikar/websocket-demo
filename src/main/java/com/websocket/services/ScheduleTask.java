package com.websocket.services;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.websocket.dto.User;

@Service
public class ScheduleTask {

    @Autowired
    private SimpMessagingTemplate template;

    // this will send a message to an endpoint on which a client can subscribe
    @Scheduled(fixedRate = 60000)
    public void trigger() {
    	JSONObject json = new JSONObject();
    	json.put("topic1 : ", new Date());
        this.template.convertAndSend("/topic/message", json );
    }

    @Scheduled(fixedRate = 30000)
    public void triggerAnotherTopic() {
    	User user = new User(Math.round(Math.random() * 1000), new Date());
        this.template.convertAndSend("/topic/user",  user);
    }
}
