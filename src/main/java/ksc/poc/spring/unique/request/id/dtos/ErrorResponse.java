package ksc.poc.spring.unique.request.id.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
	private String message;
	public ErrorResponse(String message) {
		this.message = message;
	}
}
