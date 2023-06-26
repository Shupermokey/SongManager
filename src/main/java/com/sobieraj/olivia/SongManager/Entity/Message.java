package com.sobieraj.olivia.SongManager.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

	private String message;
	private String sender;
	private MessageType type;
	
}
