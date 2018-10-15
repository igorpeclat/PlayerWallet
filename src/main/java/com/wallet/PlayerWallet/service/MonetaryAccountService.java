package com.wallet.PlayerWallet.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.PlayerWallet.entity.MonetaryAccount;
import com.wallet.PlayerWallet.exception.NoContentException;
import com.wallet.PlayerWallet.repository.MonetaryAccountRepository;

/**
 * The Class MonetaryAccountService.
 */
@Service
public class MonetaryAccountService implements IMonetaryAccountService {
	
	/** The monetary account repository. */
	@Autowired
	private MonetaryAccountRepository monetaryAccountRepository;
 
	/* (non-Javadoc)
	 * @see com.wallet.PlayerWallet.service.IMonetaryAccountService#findById(java.util.UUID)
	 */
	@Override
	public MonetaryAccount findByAccountId(UUID id) {
		return monetaryAccountRepository.findByAccountId(id).orElseThrow(() -> new NoContentException());
	}


}
