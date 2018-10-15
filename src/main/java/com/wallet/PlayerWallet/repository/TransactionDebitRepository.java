package com.wallet.PlayerWallet.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wallet.PlayerWallet.entity.TransactionDebit;


/**
 * The Interface TransactionDebitRepository.
 */
@Repository
public interface TransactionDebitRepository extends CrudRepository<TransactionDebit, UUID> {
	
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	Optional<TransactionDebit> findById(UUID id);
	
	
}
