package com.wallet.PlayerWallet.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wallet.PlayerWallet.entity.TransactionCredit;


/**
 * The Interface TransactionCreditRepository.
 */
@Repository
public interface TransactionCreditRepository extends CrudRepository<TransactionCredit, UUID> {
	
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	Optional<TransactionCredit> findById(UUID id);
	
	
}
