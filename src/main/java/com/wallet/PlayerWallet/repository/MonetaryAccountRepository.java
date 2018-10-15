package com.wallet.PlayerWallet.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wallet.PlayerWallet.entity.MonetaryAccount;


/**
 * The Interface MonetaryAccountRepository.
 */
@Repository
public interface MonetaryAccountRepository extends CrudRepository<MonetaryAccount, UUID> {
	
	/**
	 * Exists by login.
	 *
	 * @param login the login
	 * @return the boolean
	 */
	Boolean existsByLogin(String login);
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	@Query("SELECT m FROM MonetaryAccount m "
			+ "LEFT JOIN FETCH m.transactions t "
			+ "LEFT JOIN FETCH t.transactionCredit tc "
			+ "LEFT JOIN FETCH t.transactionDebit td "
			+ "where "
			+ "m.accountId = :accountId ")
	Optional<MonetaryAccount> findByAccountId(UUID accountId);
	
	
	/**
	 * Find by login.
	 *
	 * @param accountId the account id
	 * @return the optional
	 */
	Optional<MonetaryAccount> findByLogin(String login);
}
