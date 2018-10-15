package com.wallet.PlayerWallet.service;

import java.util.UUID;

import com.wallet.PlayerWallet.entity.MonetaryAccount;

/**
 * The Interface IMonetaryAccountService.
 */
public interface IMonetaryAccountService {
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the monetary account
	 */
	MonetaryAccount findByAccountId(UUID id);
}
