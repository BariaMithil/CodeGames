package com.example.CodeGames.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message{
	@JsonProperty("role")
    private String role;
	@JsonProperty("content")
    private String content;
    
	public Message(String role, String content) {
		super();
		this.role = role;
		this.content = content;
	}

//	@Override
//	public String getContent() {
//		// TODO Auto-generated method stub
//		return this.content;
//	}
//
//	@Override
//	public List<Media> getMedia() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<String, Object> getProperties() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public MessageType getMessageType() {
//		// TODO Auto-generated method stub
//		return null;
//	}
    
    
}
