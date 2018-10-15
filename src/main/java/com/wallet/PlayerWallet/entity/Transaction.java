package com.wallet.PlayerWallet.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * The Interface Transaction.
 */
@Entity
@JsonInclude(Include.NON_EMPTY)
public class Transaction extends AbstractEntity<TransactionId> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7453289530578735019L;

	/** The id. */
	@EmbeddedId
	private TransactionId id;

	/** The transaction dt. */
	@Column(name = "TRANSACTION_DT")
	@Temporal(TemporalType.DATE)
	private Date transactionDt;

	/** The list profile module. */
	@OneToOne(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TransactionCredit transactionCredit;

	/** The list profile module. */
	@OneToOne(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TransactionDebit transactionDebit;

	/** The monetary account. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID", insertable = false, updatable = false) })
	@JsonIgnore
	private MonetaryAccount monetaryAccount;
	
	/** The account id. */
	@Column(name = "ACCOUNT_ID", columnDefinition = "BINARY(16)", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private UUID accountId;

	/**
	 * Gets the transaction credit.
	 *
	 * @return the transaction credit
	 */
	public TransactionCredit getTransactionCredit() {
		return transactionCredit;
	}

	/**
	 * Sets the transaction credit.
	 *
	 * @param transactionCredit the new transaction credit
	 */
	public void setTransactionCredit(TransactionCredit transactionCredit) {
		this.transactionCredit = transactionCredit;
	}

	/**
	 * Gets the transaction debit.
	 *
	 * @return the transaction debit
	 */
	public TransactionDebit getTransactionDebit() {
		return transactionDebit;
	}

	/**
	 * Sets the transaction debit.
	 *
	 * @param transactionDebit the new transaction debit
	 */
	public void setTransactionDebit(TransactionDebit transactionDebit) {
		this.transactionDebit = transactionDebit;
	}

	/**
	 * Gets the transaction dt.
	 *
	 * @return the transaction dt
	 */
	public Date getTransactionDt() {
		return transactionDt;
	}

	/**
	 * Sets the transaction dt.
	 *
	 * @param transactionDt the new transaction dt
	 */
	public void setTransactionDt(Date transactionDt) {
		this.transactionDt = transactionDt;
	}

	/**
	 * Gets the monetary account.
	 *
	 * @return the monetary account
	 */
	public MonetaryAccount getMonetaryAccount() {
		return monetaryAccount;
	}

	/**
	 * Sets the monetary account.
	 *
	 * @param monetaryAccount the new monetary account
	 */
	public void setMonetaryAccount(MonetaryAccount monetaryAccount) {
		this.monetaryAccount = monetaryAccount;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public TransactionId getId() {
		if(null == id) {
			id =  new TransactionId();
		}
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(TransactionId id) {
		this.id = id;
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
