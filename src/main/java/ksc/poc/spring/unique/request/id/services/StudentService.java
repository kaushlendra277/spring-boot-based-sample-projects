package ksc.poc.spring.unique.request.id.services;

import org.springframework.context.annotation.Configuration;

import ksc.poc.spring.unique.request.id.dtos.TestStudentResponse;
import lombok.extern.slf4j.Slf4j;

@Configuration // it is combination of annotation and one of them is @Component
@Slf4j
public class StudentService {
	public TestStudentResponse test() {
		String test = "HelloWorld";
		log.debug(test);
		log.error(test);
		log.info(test);
		log.trace(test);
		log.warn(test);
		return new TestStudentResponse();
	}
}
