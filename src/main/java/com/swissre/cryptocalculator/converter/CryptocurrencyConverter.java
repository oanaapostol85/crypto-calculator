package com.swissre.cryptocalculator.converter;

import java.math.BigDecimal;

public class CryptocurrencyConverter {

	private String cryptocurrency;

	private BigDecimal cryptocurrencyAmount;

	private String currency;

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
