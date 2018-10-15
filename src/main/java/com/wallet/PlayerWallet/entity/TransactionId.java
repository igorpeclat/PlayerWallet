package com.wallet.PlayerWallet.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wallet.PlayerWallet.pojo.AbstractPojo;

/**
 * The Class TransactionId.
 */
@Embeddable
public class TransactionId extends AbstractPojo {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5627407195567987185L;

	/** The transaction id. */
	@Column(name = "TRANSACTION_ID", columnDefinition = "BINARY(16)", nullable = false, insertable = false, updatable = false)
	private UUID transactionId;

	/** The account id. */
	@Column(name = "ACCOUNT_ID", columnDefinition = "BINARY(16)", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private UUID accountId;

	/**
	 * Gets the transaction id.
	 *
	 * @return the transaction id
	 */
	public UUID getTransactionId() {
		return transactionId;
	}

	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId the new transaction id
	 */
	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	public UUID getAccountId() {
		return accountId;
	}

	/**
	 * Sets the account id.
	 *
	 * @param accountId the new account id
	 */
	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}
}
