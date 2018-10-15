package com.wallet.PlayerWallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class WalletInsufficientFundsException.
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason="Wallet hasn't enough funds. Credit more, please!")
public class WalletInsufficientFundsException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3062974177842079333L;

	/**
	 * Instantiates a new wallet insufficient funds exception.
	 */
	public WalletInsufficientFundsException() {
		super();
	}

	/**
	 * Instantiates a new wallet insufficient funds exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param params the params
	 */
	public WalletInsufficientFundsException(String message, Throwable cause, Object... params) {
		super(String.format(message, params), cause);
	}

	/**
	 * Instantiates a new wallet insufficient funds exception.
	 *
	 * @param message the message
	 * @param params the params
	 */
	public WalletInsufficientFundsException(String message, Object... params) {
		super(String.format(message, params));
	}

	/**
	 * Instantiates a new wallet insufficient funds exception.
	 *
	 * @param cause the cause
	 */
	public WalletInsufficientFundsException(Throwable cause) {
		super(cause);
	}
}