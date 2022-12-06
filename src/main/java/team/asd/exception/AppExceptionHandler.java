package team.asd.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorInfo handleValidationException(MethodArgumentNotValidException e) {
		String message = e.getBindingResult().getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining("; "));
		return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorInfo catchValidationException(ValidationException e) {
		return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorInfo catchRequiredParamsException(MissingServletRequestParameterException e) {
		return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorInfo catchUnexpectedException(Exception e) {
		System.out.println(e.getMessage());
		return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), "Unexpected error");
	}
}
