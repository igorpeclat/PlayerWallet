package com.wallet.PlayerWallet;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wallet.PlayerWallet.entity.MonetaryAccount;
import com.wallet.PlayerWallet.repository.MonetaryAccountRepository;

/**
 * The Class PlayerWalletApplicationTests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayerWalletApplicationTests {

	/** The mvc. */
	@Autowired
	private MockMvc mvc;

	/** The monetary account. */
	private MonetaryAccount monetaryAccount;

	/** The Constant MAIN_PATH. */
	private static final String MAIN_PATH = "/account/";

	/** The monetary account repository. */
	@Autowired
	private MonetaryAccountRepository monetaryAccountRepository;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		monetaryAccount = monetaryAccountRepository.findByLogin("John").orElse(null);
	}

	/**
	 * Test A find all no content.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testA_findAll_noContent() throws Exception {
		this.mvc.perform(get(MAIN_PATH)).andExpect(status().isNoContent());
	}

	/**
	 * Test B save.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testB_save() throws Exception {
		this.mvc.perform(post(MAIN_PATH).contentType(MediaType.APPLICATION_JSON)
				.content("{ \"login\" : \"John\",	\"currency\" : \"EUR\" }")).andExpect(status().isCreated())
				.andExpect(header().exists("Location")).andReturn();

	}

	/**
	 * Test C save account exist.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testC_saveAccountExist() throws Exception {
		this.mvc.perform(post(MAIN_PATH).contentType(MediaType.APPLICATION_JSON)
				.content("{ \"login\" : \"John\",	\"currency\" : \"EUR\" }")).andExpect(status().isConflict());
	}

	/**
	 * Test D find.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testD_find() throws Exception {
		this.mvc.perform(get(MAIN_PATH + monetaryAccount.getAccountId())).andExpect(status().isOk())
				.andExpect(content().string(containsString("login")))
				.andExpect(content().string(containsString("balance"))).andReturn();

	}

	/**
	 * Test E find no content.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testE_find_noContent() throws Exception {
		this.mvc.perform(get(MAIN_PATH + UUID.randomUUID().toString())).andExpect(status().isNoContent());
	}

	/**
	 * Test F credit.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testF_credit() throws Exception {
		this.mvc.perform(put(MAIN_PATH + "credit").contentType(MediaType.APPLICATION_JSON).content(
				"{\"accountId\" : \"" + monetaryAccount.getAccountId().toString() + "\",\"amount\" : \"600.00\"}"))
				.andExpect(status().isOk());
	}

	/**
	 * Test G debit.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testG_debit() throws Exception {
		this.mvc.perform(put(MAIN_PATH + "debit").contentType(MediaType.APPLICATION_JSON).content(
				"{\"accountId\" : \"" + monetaryAccount.getAccountId().toString() + "\",\"amount\" : \"50.00\"}"))
				.andExpect(status().isOk());
	}

	/**
	 * Test H debit not enough funds.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testH_debit_notEnoughFunds() throws Exception {
		this.mvc.perform(put(MAIN_PATH + "debit").contentType(MediaType.APPLICATION_JSON).content(
				"{\"accountId\" : \"" + monetaryAccount.getAccountId().toString() + "\",\"amount\" : \"15000.00\"}"))
				.andExpect(status().isNotAcceptable());
	}

}
