package com.wallet.PlayerWallet.entity.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import com.wallet.PlayerWallet.entity.Transaction;
import com.wallet.PlayerWallet.entity.TransactionCredit;
import com.wallet.PlayerWallet.entity.TransactionDebit;

/**
 * The Class TransactionsUtil.
 */
public class TransactionsUtil {
	
	/**
	 * Creates the model object.
	 *
	 * @return the optional
	 */
	public static Optional<List<Transaction>> createModelObject() {
		List<Transaction> transactions = new ArrayList<>();
		IntStream.range(0, 10).forEach(i -> {
			UUID accountId = UUID.randomUUID();
			
			Transaction transactionCreditCreation = new Transaction();
			transactionCreditCreation.getId().setTransactionId(UUID.randomUUID());
			transactionCreditCreation.setAccountId(accountId);
			transactionCreditCreation.setTransactionDt(new Date());

			TransactionCredit transactionCredit = new TransactionCredit();
			transactionCredit.setAmount(BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.HALF_UP));
			transactionCredit.setId(transactionCreditCreation.getId());
			transactionCreditCreation.setTransactionCredit(transactionCredit);
			
			transactions.add(transactionCreditCreation);
			
			Transaction transactionDebitCreation = new Transaction();
			transactionDebitCreation.getId().setTransactionId(UUID.randomUUID());
			transactionDebitCreation.setAccountId(accountId);
			transactionDebitCreation.setTransactionDt(new Date());

			TransactionDebit transactionDebit = new TransactionDebit();
			transactionDebit.setAmount(BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.HALF_UP));
			transactionDebit.setId(transactionDebitCreation.getId());
			transactionDebitCreation.setTransactionDebit(transactionDebit);
			
			transactions.add(transactionDebitCreation);
			
		});

		return Optional.of(transactions);
	}
}
