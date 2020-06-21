package com.swissre.cryptocalculator.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CryptoCurrencyConverterTest {

    private CryptoCurrencyConverter cryptoCurrencyConverter;

    @BeforeEach
    public void setUp() {
        cryptoCurrencyConverter = new CryptoCurrencyConverter() {
            @Override
            BigDecimal getConversionRate(String cryptoCurrency, String currency) {
                return BigDecimal.valueOf(8311.44);
            }
        };
    }

    @Test
    public void givenValidInputArgs_whenConvert_thenConvertedAmtIsCorrectlyReturned() {
        BigDecimal convertedAmt = cryptoCurrencyConverter.convert("BTC", BigDecimal.valueOf(22.5), "EUR");

        assertEquals(BigDecimal.valueOf(187007.4).doubleValue(), convertedAmt.doubleValue());
    }
}
