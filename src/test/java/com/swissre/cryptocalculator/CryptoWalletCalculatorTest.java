package com.swissre.cryptocalculator;

import com.swissre.cryptocalculator.converter.CryptoCurrencyConverter;
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
        CryptoCurrencyConverter cryptoCurrencyConverter = new CryptoCurrencyConverter() {
            @Override
            public BigDecimal convert(String cryptoCurrency, BigDecimal cryptoCurrencyAmount, String currency) {
                return cryptoCurrencyAmount.multiply(BigDecimal.valueOf(8311.44));
            }
        };

        cryptoWalletCalculator = new CryptoWalletCalculatorBuilder()
                .withCryptoWalletFilePath(getFilePathFromResources("bobs_crypto.txt"))
                .withCurrency("EUR")
                .withCryptoCurrencyConverter(cryptoCurrencyConverter)
                .build();
    }

    @Test
    public void givenValidInputFile_whenCalculate_thenConvertedAmtMapIsCorrectlyReturned() {
        cryptoWalletCalculator = cryptoWalletCalculator.calculate();

        Map<String, BigDecimal> cryptoCurrencyConvertedAmtMap = cryptoWalletCalculator.getCryptoCurrencyConvertedAmtMap();

        assertEquals(BigDecimal.valueOf(83114.4).doubleValue(), cryptoCurrencyConvertedAmtMap.get("BTC").doubleValue());
        assertEquals(BigDecimal.valueOf(41557.2).doubleValue(), cryptoCurrencyConvertedAmtMap.get("ETH").doubleValue());
        assertEquals(BigDecimal.valueOf(16622880).doubleValue(), cryptoCurrencyConvertedAmtMap.get("XRP").doubleValue());
    }

    @Test
    public void givenValidInputFile_whenCalculate_thenTotalAmtMapIsCorrectlyReturned() {
        cryptoWalletCalculator = cryptoWalletCalculator.calculate();

        BigDecimal totalAmt = cryptoWalletCalculator.getTotalAmt();

        assertEquals(BigDecimal.valueOf(16747551.6).doubleValue(), totalAmt.doubleValue());
    }

    @Test
    public void givenFilePathDoesNotExist_whenCalculate_thenEmptyMapIsReturned() {
        cryptoWalletCalculator = new CryptoWalletCalculatorBuilder()
                .withCryptoWalletFilePath("not_existing_file.txt")
                .withCurrency("EUR")
                .build()
                .calculate();

        Map<String, BigDecimal> cryptoCurrencyConvertedAmtMap = cryptoWalletCalculator.getCryptoCurrencyConvertedAmtMap();

        assertTrue(cryptoCurrencyConvertedAmtMap.isEmpty());
    }
}
