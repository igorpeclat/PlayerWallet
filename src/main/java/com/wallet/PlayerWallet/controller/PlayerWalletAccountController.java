package com.wallet.PlayerWallet.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wallet.PlayerWallet.entity.MonetaryAccount;
import com.wallet.PlayerWallet.entity.TransactionCredit;
import com.wallet.PlayerWallet.entity.TransactionDebit;
import com.wallet.PlayerWallet.exception.NoContentException;
import com.wallet.PlayerWallet.exception.WalletExistException;
import com.wallet.PlayerWallet.repository.MonetaryAccountRepository;
import com.wallet.PlayerWallet.service.MonetaryAccountService;
import com.wallet.PlayerWallet.service.TransactionService;

/**
 * The Class PlayerWalletAccountController.
 */
@RestController
@RequestMapping("account")
public class PlayerWalletAccountController {

	/** The monetary account repository. */
	@Autowired
	private MonetaryAccountRepository monetaryAccountRepository;

	/** The monetary account service. */
	@Autowired
	private MonetaryAccountService monetaryAccountService;

	/** The transaction service. */
	@Autowired
	private TransactionService transactionService;

	/**
	 * Save.
	 *
	 * @param monetaryAccount the monetary account
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody MonetaryAccount monetaryAccount) {
		validateLoginWalletExists(monetaryAccount.getLogin());
		monetaryAccount.setAccountId(UUID.randomUUID());
		monetaryAccount.setBalance(BigDecimal.ZERO);
		return ResponseEntity.created(buildURI(monetaryAccountRepository.save(monetaryAccount))).build();
	}

	/**
	 * Gets the account info and transactions.
	 *
	 * @param accountId the id
	 * @return the response entity
	 */
	@GetMapping("/{accountId}")
	public ResponseEntity<?> find(@PathVariable("accountId") String accountId) {
		validateFindAllMonetaryAccount();
		return ResponseEntity.ok(validateNotFoundWallet(UUID.fromString(accountId)));
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@GetMapping
	public ResponseEntity<?> findAll() {
		validateFindAllMonetaryAccount();
		return ResponseEntity.ok(monetaryAccountRepository.findAll());
	}

	/**
	 * Debit.
	 *
	 * @param transactionDebit the transaction debit
	 * @return the response entity
	 */
	@PutMapping("debit")
	public ResponseEntity<?> debit(	@Valid @RequestBody TransactionDebit transactionDebit) {
		validateNotFoundWallet(transactionDebit.getAccountId());
		return ResponseEntity.ok(transactionService.debit(transactionDebit));
	}

	/**
	 * Credit.
	 *
	 * @param transactionCredit the transaction credit
	 * @return the response entity
	 */
	@PutMapping("credit")
	public ResponseEntity<?> credit(@RequestBody TransactionCredit transactionCredit) {
		validateNotFoundWallet(transactionCredit.getAccountId());
		return ResponseEntity.ok(transactionService.credit(transactionCredit));
	}

	/**
	 * Validate account exists.
	 *
	 * @param login the login
	 */
	private void validateLoginWalletExists(String login) {
		if (monetaryAccountRepository.existsByLogin(login)) {
			throw new WalletExistException();
		}
	}

	/**
	 * Validate not found wallet.
	 *
	 * @param id the id
	 * @return the monetary account
	 */
	private MonetaryAccount validateNotFoundWallet(UUID id) {
		return monetaryAccountService.findByAccountId(id);
	}

	/**
	 * Validate find all monetary account.
	 */
	private void validateFindAllMonetaryAccount() {
		if (monetaryAccountRepository.count() == 0) {
			throw new NoContentException();
		}
	}

	/**
	 * Builds the URI.
	 *
	 * @param monetaryAccount the monetary account
	 * @return the uri
	 */
	private URI buildURI(MonetaryAccount monetaryAccount) {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(monetaryAccount.getAccountId()).toUri();
		return location;
	}

}
