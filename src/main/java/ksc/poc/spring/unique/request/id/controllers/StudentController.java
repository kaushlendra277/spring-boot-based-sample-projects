package ksc.poc.spring.unique.request.id.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ksc.poc.spring.unique.request.id.dtos.GResponse;
import ksc.poc.spring.unique.request.id.dtos.TestStudentResponse;
import ksc.poc.spring.unique.request.id.helpers.RequestHelper;
import ksc.poc.spring.unique.request.id.services.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {
	
	private final RequestHelper requestHelper;
	
	private final StudentService studentService;
	
	@GetMapping
	public ResponseEntity<GResponse<TestStudentResponse>> test() {
		GResponse<TestStudentResponse> response = GResponse
				.<TestStudentResponse>builder()
				.corelationId(requestHelper.getCorelationId())
				.data(studentService.test()).build();
		return ResponseEntity
				.ok(response);
	}

}
