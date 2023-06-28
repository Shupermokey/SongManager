package com.sobieraj.olivia.SongManager.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import com.sobieraj.olivia.SongManager.Entity.Message;

@Controller
public class ChatController {
	
	
	@GetMapping("/chat")
	public String charRoom() {
		return "chat";
	}
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public Message sendMessage(@Payload Message chatMessage) {
		return chatMessage;
		
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public Message adduser(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
		
	}
}
