package com.wallet.PlayerWallet.util;

import java.math.BigDecimal;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

/**
 * The Class MonetaryUtils.
 */
public abstract class MonetaryUtils {

	/** The currency. */
	private static CurrencyUnit currency = Monetary.getCurrency("EUR");

	/**
	 * To money currency.
	 *
	 * @param moneyValue the money value
	 * @return the monetary amount
	 */
	public static MonetaryAmount toMonenataryAmount(BigDecimal moneyValue) {
		return FastMoney.of(moneyValue, currency);
	}

	/**
	 * To big decimal.
	 *
	 * @param monetaryAmount the monetary amount
	 * @return the big decimal
	 */
	public static BigDecimal toBigDecimal(MonetaryAmount monetaryAmount) {
		return BigDecimal.valueOf(monetaryAmount.getNumber().doubleValue());
	}

}
