package com.swissre.cryptocalculator.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CryptoCurrencyConverterClientTest {

    private CryptoCurrencyConverterClient cryptoCurrencyConverterClient;

    @BeforeEach
    public void setUp() {
        cryptoCurrencyConverterClient = new CryptoCurrencyConverterClient() {
            @Override
            HttpURLConnection getConnection(URL url) {
                return new MockHttpURLConnection(200, "{\"EUR\":8311.5}");
            }
        };
    }

    @Test
    public void givenValidInputArgs_whenGetUrl_thenValidUrlIsReturned() {
        URL url = cryptoCurrencyConverterClient.getUrl("BTC", "EUR");

        assertEquals("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=EUR", url.toString());
    }

    @Test
    public void givenSuccessResponseCode_whenGetResponseCode_thenResponseCodeIsReturned() {
        int responseCode = cryptoCurrencyConverterClient.getResponseCode(new MockHttpURLConnection(200, null));

        assertEquals(200, responseCode);
    }

    @Test
    public void givenInvalidConnection_whenGetResponseCode_thenException() {
        assertThrows(ConnectionFailedException.class,
                () -> cryptoCurrencyConverterClient.getResponseCode(new MockHttpURLConnection(null, null) {
                    @Override
                    public int getResponseCode() throws IOException {
                        throw new IOException("Unable to connect to server!");
                    }
                }));
    }

    @Test
    public void givenValidResponse_whenReadConversionRateFromResponse_thenConversionRateIsReturned() {
        BigDecimal conversionRate = cryptoCurrencyConverterClient.readConversionRateFromResponse(new MockHttpURLConnection(200, "{\"EUR\":8311.5}"));
        assertEquals(8311.5, conversionRate.doubleValue());
    }

    @Test
    public void givenWrongResponseBodyFormat_whenReadConversionRateFromResponse_thenException() {
        assertThrows(InvalidCryptoCurrencyResponseException.class,
                () -> cryptoCurrencyConverterClient.readConversionRateFromResponse(new MockHttpURLConnection(200, "8311.5")));
    }

    @Test
    public void givenWrongConversionRateFormatInResponseBody_whenReadConversionRateFromResponse_thenException() {
        assertThrows(InvalidCryptoCurrencyResponseException.class,
                () -> cryptoCurrencyConverterClient.readConversionRateFromResponse(new MockHttpURLConnection(200, "{\"EUR\":8311.5d}")));
    }

    @Test
    public void givenNotFoundResponseCode_whenReadConversionRateFromResponse_thenException() {
        assertThrows(InvalidCryptoCurrencyResponseException.class,
                () -> cryptoCurrencyConverterClient.readConversionRateFromResponse(new MockHttpURLConnection(404, null)));
    }

    @Test
    public void givenInvalidConnection_whenReadConversionRateFromResponse_thenException() {
        assertThrows(InvalidCryptoCurrencyResponseException.class,
                () -> cryptoCurrencyConverterClient.readConversionRateFromResponse(new MockHttpURLConnection(200, null) {
                    @Override
                    public InputStream getInputStream() throws IOException {
                        throw new IOException("Unable to connect to server!");
                    }
                }));
    }

    @Test
    public void givenValidResponse_whenFindConversionRate_thenConversionRateIsReturned() {
        BigDecimal conversionRate = cryptoCurrencyConverterClient.findConversionRate("BTC", "EUR");
        assertEquals(8311.5, conversionRate.doubleValue());
    }
}
