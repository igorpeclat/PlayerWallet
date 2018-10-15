package com.wallet.PlayerWallet.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.wallet.PlayerWallet.entity.util.TransactionsUtil;

/**
 * The Class TransactionRepositoryTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionRepositoryTest {

	/** The transaction repository. */
	@Autowired
	private TransactionRepository transactionRepository;
	
	/** The id. */
	UUID id = UUID.randomUUID();

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		transactionRepository = mock(TransactionRepository.class);
		when(transactionRepository.findAllById(id)).thenReturn(TransactionsUtil.createModelObject());
	}

	/**
	 * Test A find all by id.
	 */
	@Test
	public void testA_hasSize_findAllById() {
		assertThat(transactionRepository.findAllById(id).orElse(null), hasSize(20));
	}
	
	/**
	 * Test B find all by id.
	 */
	@Test
	public void testB_isNull_findAllById() {
		assertThat(transactionRepository.findAllById(UUID.randomUUID()).orElse(null), equalTo(null));
	}

}
