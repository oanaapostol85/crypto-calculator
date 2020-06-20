package com.swissre.cryptocalculator.converter;

import java.math.BigDecimal;

public class CryptocurrencyConverter {

	private final String cryptocurrency;

	private final BigDecimal cryptocurrencyAmount;

	private final String currency;

	public CryptocurrencyConverter(String cryptocurrency, BigDecimal cryptocurrencyAmount, String currency) {
		this.cryptocurrency = cryptocurrency;
		this.cryptocurrencyAmount = cryptocurrencyAmount;
		this.currency = currency;
	}

	public BigDecimal convert() {
		return cryptocurrencyAmount.multiply(getConversionRate());
	}

	private BigDecimal getConversionRate() {
		return new CryptocurrencyConverterClient().findConversionRate(cryptocurrency, currency);
	}
}
