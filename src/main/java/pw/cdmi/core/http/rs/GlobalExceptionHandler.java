package pw.cdmi.core.http.rs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pw.cdmi.core.http.Headers;
import pw.cdmi.core.http.exception.AWSException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AWSException.class)
	public ResponseEntity<String> beanValidation(AWSException exception) {
		return ResponseEntity.status(exception.getHttpStatus())
				.header(Headers.SERVER, "order-service") //微服务名称
				.body(exception.getXmlText());
	}
}
