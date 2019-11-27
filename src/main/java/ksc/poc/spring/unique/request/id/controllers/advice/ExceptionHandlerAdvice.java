package ksc.poc.spring.unique.request.id.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ksc.poc.spring.unique.request.id.dtos.ErrorResponse;
import ksc.poc.spring.unique.request.id.dtos.GResponse;
import ksc.poc.spring.unique.request.id.interceptors.dtos.MyRequestDetailsHolder;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<GResponse<ErrorResponse>> handleException(Exception ex) {
        GResponse<ErrorResponse> errorResponse = new GResponse<>();
        errorResponse.setErrored(true);
        errorResponse.setCorelationId(MyRequestDetailsHolder.getCorelationId());
        errorResponse.setData(new ErrorResponse(ex.getMessage()));
        
		return ResponseEntity
        		.status(HttpStatus.INTERNAL_SERVER_ERROR)
        		.body(errorResponse);
    }
	
}
