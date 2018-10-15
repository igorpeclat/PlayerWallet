package com.wallet.PlayerWallet.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wallet.PlayerWallet.entity.Transaction;


/**
 * The Interface TransactionRepository.
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
	
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	Optional<Transaction> findById(UUID id);
	
	
	
	/**
	 * Find all by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Query("SELECT t FROM Transaction t JOIN TransactionCredit tc JOIN TransactionDebit td where t.id = :id ")
	Optional<List<Transaction>> findAllById(UUID id);
	
}
