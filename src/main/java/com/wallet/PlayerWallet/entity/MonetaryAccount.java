package com.wallet.PlayerWallet.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wallet.PlayerWallet.pojo.AbstractPojo;
import com.wallet.PlayerWallet.serializer.MoneyDeserializer;
import com.wallet.PlayerWallet.serializer.MoneySerializer;

/**
 * The Class MonetaryAccount.
 */
@Entity
@Table(name = "MONETARY_ACCOUNT")
@JsonInclude(Include.NON_EMPTY)
public class MonetaryAccount extends AbstractPojo {

	private static final long serialVersionUID = 7670507857917589838L;

	/** The uuid. */
	@Id
	@Column(name = "ACCOUNT_ID", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
	@JsonIgnore
	private UUID accountId;

	/** The login. */
	@NotNull
	@Column(name = "LOGIN")
	private String login;

	/** The balance. */
	@Column(name = "BALANCE")
	@JsonProperty(access = Access.READ_ONLY)
	@JsonSerialize(using = MoneySerializer.class)
	@JsonDeserialize(using = MoneyDeserializer.class)
	private BigDecimal balance;
	
	/** The currency. */
	@Column(name = "CURRENCY")
	private String currency;
	
    /** The transactions. */
    @OneToMany(mappedBy = "monetaryAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transaction> transactions;


	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Sets the balance.
	 *
	 * @param balance the new balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the currency.
	 *
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets the currency.
	 *
	 * @param currency the new currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Gets the transactions.
	 *
	 * @return the transactions
	 */
	public Set<Transaction> getTransactions() {
		if(null == transactions) {
			transactions = new HashSet<>();
		}
		return transactions;
	}

	/**
	 * Sets the transactions.
	 *
	 * @param transactions the new transactions
	 */
	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
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
