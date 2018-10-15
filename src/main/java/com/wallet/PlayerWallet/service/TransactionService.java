package com.wallet.PlayerWallet.service;

import java.math.BigDecimal;
import java.util.UUID;

import javax.money.MonetaryAmount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.PlayerWallet.entity.MonetaryAccount;
import com.wallet.PlayerWallet.entity.Transaction;
import com.wallet.PlayerWallet.entity.TransactionCredit;
import com.wallet.PlayerWallet.entity.TransactionDebit;
import com.wallet.PlayerWallet.exception.WalletInsufficientFundsException;
import com.wallet.PlayerWallet.repository.MonetaryAccountRepository;
import com.wallet.PlayerWallet.util.MonetaryUtils;

/**
 * The Class TransactionService.
 */
@Service
public class TransactionService implements ITransactionService {

	/** The monetary account repository. */
	@Autowired
	private MonetaryAccountRepository monetaryAccountRepository;

	/* (non-Javadoc)
	 * @see com.wallet.PlayerWallet.service.ITransactionService#credit(com.wallet.PlayerWallet.entity.TransactionCredit)
	 */
	@Override
	public Transaction credit(TransactionCredit transactionCredit) {
		MonetaryAccount monetaryAccount = monetaryAccountRepository.findById(transactionCredit.getAccountId()).get();
		MonetaryAmount monetaryBalance = MonetaryUtils.toMonenataryAmount(monetaryAccount.getBalance());
		MonetaryAmount transactionAmount = MonetaryUtils.toMonenataryAmount(transactionCredit.getAmount());
		MonetaryAmount monetaryBalanceDifference = monetaryBalance.add(transactionAmount);
		Transaction transaction = new Transaction();
		UUID transactionID = UUID.randomUUID();
		transaction.getId().setTransactionId(transactionID);
		transaction.getId().setAccountId(monetaryAccount.getAccountId());
		transactionCredit.getId().setAccountId(monetaryAccount.getAccountId());
		transactionCredit.getId().setTransactionId(transactionID);
		transactionCredit.setAmount(MonetaryUtils.toBigDecimal(transactionAmount));
		transaction.setTransactionCredit(transactionCredit);
		monetaryAccount.setBalance(MonetaryUtils.toBigDecimal(monetaryBalanceDifference));
		monetaryAccount.getTransactions().add(transaction);
		monetaryAccountRepository.save(monetaryAccount);
		return transaction;
	}

	/* (non-Javadoc)
	 * @see com.wallet.PlayerWallet.service.ITransactionService#debit(com.wallet.PlayerWallet.entity.TransactionDebit)
	 */
	@Override
	public Transaction debit(TransactionDebit transactionDebit) {
		MonetaryAccount monetaryAccount = monetaryAccountRepository.findById(transactionDebit.getAccountId()).get();
		MonetaryAmount monetaryBalance = MonetaryUtils.toMonenataryAmount(monetaryAccount.getBalance());
		MonetaryAmount transactionAmount = MonetaryUtils.toMonenataryAmount(transactionDebit.getAmount());
		MonetaryAmount monetaryBalanceDifference = monetaryBalance.subtract(transactionAmount);
		if (monetaryBalanceDifference.isLessThan(MonetaryUtils.toMonenataryAmount(BigDecimal.ZERO))) {
			throw new WalletInsufficientFundsException();
		}
		Transaction transaction = new Transaction();
		UUID transactionID = UUID.randomUUID();
		transaction.getId().setTransactionId(transactionID);
		transaction.getId().setAccountId(monetaryAccount.getAccountId());
		transactionDebit.getId().setAccountId(monetaryAccount.getAccountId());
		transactionDebit.getId().setTransactionId(transactionID);
		transactionDebit.setAmount(MonetaryUtils.toBigDecimal(transactionAmount));
		transaction.setTransactionDebit(transactionDebit);
		monetaryAccount.setBalance(MonetaryUtils.toBigDecimal(monetaryBalanceDifference));
		monetaryAccount.getTransactions().add(transaction);
		monetaryAccountRepository.save(monetaryAccount);
		return transaction;
	}

}
