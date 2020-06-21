package com.swissre.cryptocalculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Map;

import static com.swissre.cryptocalculator.TestUtils.getFilePathFromResources;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CryptoWalletFileReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {"bobs_crypto.txt", "bobs_crypto_empty_lines.txt", "bobs_crypto_whitespaces.txt"})
    public void givenValidInputFile_whenReadFile_thenCryptocurrencyAmtMapIsCorrectlyReturned(String fileName) {
        CryptoWalletFileReader cryptoWalletFileReader = new CryptoWalletFileReader();

        Map<String, BigDecimal> cryptoCurrencyAmtMap = cryptoWalletFileReader.readFile(getFilePathFromResources(fileName));

        assertEquals(BigDecimal.TEN, cryptoCurrencyAmtMap.get("BTC"));
        assertEquals(BigDecimal.valueOf(5), cryptoCurrencyAmtMap.get("ETH"));
        assertEquals(BigDecimal.valueOf(2000), cryptoCurrencyAmtMap.get("XRP"));
    }

    @Test
    public void givenFilePathDoesNotExist_whenReadFile_thenEmptyMapIsReturned() {
        CryptoWalletFileReader cryptoWalletFileReader = new CryptoWalletFileReader();

        Map<String, BigDecimal> cryptoCurrencyAmtMap = cryptoWalletFileReader.readFile("not_existing_file.txt");
        assertTrue(cryptoCurrencyAmtMap.isEmpty());
    }
}
