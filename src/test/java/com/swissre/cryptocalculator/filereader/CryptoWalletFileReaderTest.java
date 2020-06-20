package com.swissre.cryptocalculator.filereader;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static com.swissre.cryptocalculator.TestUtils.getFilePathFromResources;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CryptoWalletFileReaderTest {

    @Test
    public void givenValidInputFile_whenReadFile_thenCryptocurrencyAmtMapIsCorrectlyReturned() {
        CryptoWalletFileReader cryptoWalletFileReader = new CryptoWalletFileReader(
                getFilePathFromResources("bobs_crypto.txt"));

        Map<String, BigDecimal> cryptocurrencyAmtMap = cryptoWalletFileReader.readFile();

        assertCryptocurrencyAmtMap(cryptocurrencyAmtMap);
    }

    @Test
    public void givenFileContainsEmptyLines_whenReadFile_thenCryptocurrencyAmtMapIsCorrectlyReturned() {
        CryptoWalletFileReader cryptoWalletFileReader = new CryptoWalletFileReader(
                getFilePathFromResources("bobs_crypto_empty_lines.txt"));

        Map<String, BigDecimal> cryptocurrencyAmtMap = cryptoWalletFileReader.readFile();

        assertCryptocurrencyAmtMap(cryptocurrencyAmtMap);
    }

    @Test
    public void givenFileContainsWhitespaces_whenReadFile_thenCryptocurrencyAmtMapIsCorrectlyReturned() {
        CryptoWalletFileReader cryptoWalletFileReader = new CryptoWalletFileReader(
                getFilePathFromResources("bobs_crypto_whitespaces.txt"));

        Map<String, BigDecimal> cryptocurrencyAmtMap = cryptoWalletFileReader.readFile();

        assertCryptocurrencyAmtMap(cryptocurrencyAmtMap);
    }

    @Test
    public void givenFilePathDoesNotExist_whenReadFile_thenEmptyMapIsReturned() {
        CryptoWalletFileReader cryptoWalletFileReader = new CryptoWalletFileReader(
                "not_existing_file.txt");

        Map<String, BigDecimal> cryptocurrencyAmtMap = cryptoWalletFileReader.readFile();
        assertTrue(cryptocurrencyAmtMap.isEmpty());
    }

    private void assertCryptocurrencyAmtMap(Map<String, BigDecimal> cryptocurrencyAmtMap) {
        assertEquals(BigDecimal.TEN, cryptocurrencyAmtMap.get("BTC"));
        assertEquals(BigDecimal.valueOf(5), cryptocurrencyAmtMap.get("ETH"));
        assertEquals(BigDecimal.valueOf(2000), cryptocurrencyAmtMap.get("XRP"));
    }
}
