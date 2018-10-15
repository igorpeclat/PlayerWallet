package com.wallet.PlayerWallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class WalletExistException.
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason="Wallet account already exist. Try another one, please!")
public class WalletExistException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3062974177842079333L;

	/**
	 * Instantiates a new wallet exist exception.
	 */
	public WalletExistException() {
		super();
	}

	/**
	 * Instantiates a new Wallet account exist exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param params the params
	 */
	public WalletExistException(String message, Throwable cause, Object... params) {
		super(String.format(message, params), cause);
	}

	/**
	 * Instantiates a Wallet account exist exception.
	 *
	 * @param message the message
	 * @param params the params
	 */
	public WalletExistException(String message, Object... params) {
		super(String.format(message, params));
	}

	/**
	 * Instantiates a new Wallet account exist exception.
	 *
	 * @param cause the cause
	 */
	public WalletExistException(Throwable cause) {
		super(cause);
	}
}