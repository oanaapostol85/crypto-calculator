package com.swissre.cryptocalculator;

import com.swissre.cryptocalculator.converter.CryptoCurrencyConverter;

public class CryptoWalletCalculatorBuilder {

    private String cryptoWalletFilePath;

    private String currency;

    private CryptoCurrencyConverter cryptoCurrencyConverter;

    public CryptoWalletCalculatorBuilder withCryptoWalletFilePath(String cryptoWalletFilePath) {
        this.cryptoWalletFilePath = cryptoWalletFilePath;
        return this;
    }

    public CryptoWalletCalculatorBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public CryptoWalletCalculatorBuilder withCryptoCurrencyConverter(CryptoCurrencyConverter cryptoCurrencyConverter) {
        this.cryptoCurrencyConverter = cryptoCurrencyConverter;
        return this;
    }

    public CryptoWalletCalculator build() {
        CryptoWalletCalculator cryptoWalletCalculator = new CryptoWalletCalculator();
        cryptoWalletCalculator.setCryptoWalletFilePath(cryptoWalletFilePath);
        cryptoWalletCalculator.setCurrency(currency);
        if (cryptoCurrencyConverter == null) {
            cryptoCurrencyConverter = new CryptoCurrencyConverter();
        }
        cryptoWalletCalculator.setCryptoCurrencyConverter(cryptoCurrencyConverter);

        return cryptoWalletCalculator;
    }
}
