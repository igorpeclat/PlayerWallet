package com.wallet.PlayerWallet.service;

import com.wallet.PlayerWallet.entity.Transaction;
import com.wallet.PlayerWallet.entity.TransactionCredit;
import com.wallet.PlayerWallet.entity.TransactionDebit;

/**
 * The Interface ITransactionService.
 */
public interface ITransactionService {

	/**
	 * Credit.
	 *
	 * @param transactionCredit the transaction credit
	 * @return the transaction
	 */
	Transaction credit(TransactionCredit transactionCredit);

	/**
	 * Debit.
	 *
	 * @param transactionDebit the transaction debit
	 * @return the transaction
	 */
	Transaction debit(TransactionDebit transactionDebit);

}
