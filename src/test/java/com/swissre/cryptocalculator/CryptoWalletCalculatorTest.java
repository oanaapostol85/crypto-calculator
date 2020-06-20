package com.swissre.cryptocalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static com.swissre.cryptocalculator.TestUtils.getFilePathFromResources;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CryptoWalletCalculatorTest {

    private CryptoWalletCalculator cryptoWalletCalculator;

    @BeforeEach
    public void setUp() {
        cryptoWalletCalculator = new CryptoWalletCalculator()
                .withCryptoWalletFilePath(getFilePathFromResources("bobs_crypto.txt"))
                .withCurrency("EUR");
    }

    @Test
    public void givenValidInputFile_whenCalculate_thenConvertedAmtMapIsCorrectlyReturned() {
        cryptoWalletCalculator = cryptoWalletCalculator.calculate();

        Map<String, BigDecimal> cryptocurrencyConvertedAmtMap = cryptoWalletCalculator.getCryptocurrencyConvertedAmtMap();

        assertEquals(BigDecimal.valueOf(83114.4).doubleValue(), cryptocurrencyConvertedAmtMap.get("BTC").doubleValue());
        assertEquals(BigDecimal.valueOf(41557.2).doubleValue(), cryptocurrencyConvertedAmtMap.get("ETH").doubleValue());
        assertEquals(BigDecimal.valueOf(16622880).doubleValue(), cryptocurrencyConvertedAmtMap.get("XRP").doubleValue());
    }

    @Test
    public void givenValidInputFile_whenCalculateTotalAmt_thenTotalAmtIsReturned() {
        cryptoWalletCalculator = cryptoWalletCalculator.calculate();

        BigDecimal totalAmt = cryptoWalletCalculator.calculateTotalAmt();

        assertEquals(BigDecimal.valueOf(16747551.6).doubleValue(), totalAmt.doubleValue());
    }

    @Test
    public void givenFilePathDoesNotExist_whenCalculate_thenEmptyMapIsReturned() {
        cryptoWalletCalculator = cryptoWalletCalculator
                .withCryptoWalletFilePath("not_existing_file.txt")
                .calculate();

        Map<String, BigDecimal> cryptocurrencyConvertedAmtMap = cryptoWalletCalculator.getCryptocurrencyConvertedAmtMap();

        assertTrue(cryptocurrencyConvertedAmtMap.isEmpty());
    }
}
