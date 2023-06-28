package com.sobieraj.olivia.SongManager.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

	private MessageType type;
    private String content;
    private String sender;
	
}
