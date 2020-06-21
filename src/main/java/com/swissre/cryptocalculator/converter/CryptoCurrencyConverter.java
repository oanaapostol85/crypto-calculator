package com.swissre.cryptocalculator.converter;

import java.math.BigDecimal;

public class CryptoCurrencyConverter {

    public BigDecimal convert(String cryptoCurrency, BigDecimal cryptoCurrencyAmount, String currency) {
        return cryptoCurrencyAmount.multiply(getConversionRate(cryptoCurrency, currency));
    }

    BigDecimal getConversionRate(String cryptoCurrency, String currency) {
        return new CryptoCurrencyConverterClient()
                .findConversionRate(cryptoCurrency, currency);
    }
}
