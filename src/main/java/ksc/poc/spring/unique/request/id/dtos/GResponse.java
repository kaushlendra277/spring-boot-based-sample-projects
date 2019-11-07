package ksc.poc.spring.unique.request.id.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GResponse<T> {
	private boolean errored;
	private String corelationId;
	private T data;
	public GResponse(String corelationId, T data) {
		this.data = data;
		this.corelationId = corelationId;
	}
	
}
