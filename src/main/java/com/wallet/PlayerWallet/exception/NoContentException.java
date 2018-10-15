package com.wallet.PlayerWallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class NoContentException.
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason="No content found on requested search. Try another one, please!")
public class NoContentException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3062974177842079333L;

	/**
	 * Instantiates a new no content exception.
	 */
	public NoContentException() {
		super();
	}

	/**
	 * Instantiates a new no content exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param params the params
	 */
	public NoContentException(String message, Throwable cause, Object... params) {
		super(String.format(message, params), cause);
	}

	/**
	 * Instantiates a new no content exception.
	 *
	 * @param message the message
	 * @param params the params
	 */
	public NoContentException(String message, Object... params) {
		super(String.format(message, params));
	}

	/**
	 * Instantiates a new no content exception.
	 *
	 * @param cause the cause
	 */
	public NoContentException(Throwable cause) {
		super(cause);
	}
}