package com.example.CodeGames.Dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response {
    private List<Choice> choices;

	private byte[] content;

	private String name = "Use Case Document";

    @Getter
    @Setter
    public static class Choice{
        private String message;
        private Integer index;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Integer getIndex() {
			return index;
		}
		public void setIndex(Integer index) {
			this.index = index;
		}
        
    }

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
    
    
}

