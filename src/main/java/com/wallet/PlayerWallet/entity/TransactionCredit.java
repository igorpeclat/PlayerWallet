package com.wallet.PlayerWallet.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wallet.PlayerWallet.serializer.MoneyDeserializer;
import com.wallet.PlayerWallet.serializer.MoneySerializer;

/**
 * The Class TransactionCredit.
 */
@Entity
@Table(name = "TRANSACTION_CREDIT")
public class TransactionCredit extends AbstractEntity<TransactionId> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2346061102043909212L;

	/** The id. */
	@EmbeddedId
	@JsonIgnore
	private TransactionId id;

	/** The amount. */
	@Column(name = "TRANSACTION_AMOUNT")
	@JsonSerialize(using = MoneySerializer.class)
	@JsonDeserialize(using = MoneyDeserializer.class)
	private BigDecimal amount;

	/** The profile model. */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "TRANSACTION_ID", referencedColumnName = "TRANSACTION_ID", insertable = false, updatable = false),
			@JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID", insertable = false, updatable = false) })
	@JsonIgnore
	private Transaction transaction;

	/** The account id. */
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "ACCOUNT_ID", columnDefinition = "BINARY(16)", nullable = false, insertable = false, updatable = false)
	private UUID accountId;

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Gets the transaction.
	 *
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * Sets the transaction.
	 *
	 * @param transaction the new transaction
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
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

	/* (non-Javadoc)
	 * @see com.wallet.PlayerWallet.entity.AbstractEntity#getId()
	 */
	public TransactionId getId() {
		if(null == id) {
			id =  new TransactionId();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see com.wallet.PlayerWallet.entity.AbstractEntity#setId(com.wallet.PlayerWallet.pojo.AbstractPojo)
	 */
	public void setId(TransactionId id) {
		this.id = id;
	}


}
