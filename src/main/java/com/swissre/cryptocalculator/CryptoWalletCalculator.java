package com.swissre.cryptocalculator;

import com.swissre.cryptocalculator.converter.CryptocurrencyConverter;
import com.swissre.cryptocalculator.filereader.CryptoWalletFileReader;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class CryptoWalletCalculator {

	private String cryptoWalletFilePath;

	private String currency;

	private Map<String, BigDecimal> cryptocurrencyConvertedAmtMap;

	public CryptoWalletCalculator withCryptoWalletFilePath(String cryptoWalletFilePath) {
		this.cryptoWalletFilePath = cryptoWalletFilePath;
		return this;
	}

	public CryptoWalletCalculator withCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public CryptoWalletCalculator calculate() {
		Map<String, BigDecimal> cryptocurrencyAmtMap = new CryptoWalletFileReader(cryptoWalletFilePath)
				.readFile();

		cryptocurrencyConvertedAmtMap = cryptocurrencyAmtMap.entrySet()
				.parallelStream()
				.collect(Collectors.toMap(Map.Entry::getKey,
						cryptocurrencyAmtEntry -> new CryptocurrencyConverter(cryptocurrencyAmtEntry.getKey(),
								cryptocurrencyAmtEntry.getValue(),
								currency)
								.convert()));
		return this;
	}

	public void prettyPrint() {
		cryptocurrencyConvertedAmtMap
				.forEach((cryptocurrency, price) ->
						System.out.println(format("%s=%.2f %s", cryptocurrency, price, currency)));

		System.out.println(format("Total=%.2f %s", calculateTotalAmt(), currency));
	}

	BigDecimal calculateTotalAmt() {
		return cryptocurrencyConvertedAmtMap.values()
				.stream()
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	Map<String, BigDecimal> getCryptocurrencyConvertedAmtMap() {
		return cryptocurrencyConvertedAmtMap;
	}
}
