package ksc.poc.spring.unique.request.id.helpers;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ToString
public class RequestHelper {
	@Getter
	@Setter
	private String corelationId; 
}
