package com.wallet.PlayerWallet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

/**
 * The Class ExceptionHandlingControllerAdvice.
 */
@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

	/**
	 * Handle field validation exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(value = { IllegalArgumentException.class, InvalidFormatException.class })
	public ResponseEntity<?> handleFieldValidationException(final Exception ex) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}

}