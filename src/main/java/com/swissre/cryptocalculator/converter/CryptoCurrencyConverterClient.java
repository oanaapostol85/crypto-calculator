package com.swissre.cryptocalculator.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class CryptoCurrencyConverterClient {

    private static final String CRYPTOCURRENCY_TO_CURRENCY_URL_FORMAT = "https://min-api.cryptocompare.com/data/price?%s=%s&%s=%s";
    private static final String CRYPTOCURRENCY_QUERY_PARAM = "fsym";
    private static final String CURRENCY_PARAM = "tsyms";
    private static final String CRYPTOCURRENCY_TO_CURRENCY_RESPONSE_BODY_FORMAT = "\\{\".*\":([0-9.]*)}";

    public BigDecimal findConversionRate(String cryptoCurrency, String currency) {
        URL url = getUrl(cryptoCurrency, currency);

        HttpURLConnection connection = null;
        try {
            connection = getConnection(url);
            return readConversionRateFromResponse(connection);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private HttpURLConnection getConnection(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            return connection;
        } catch (IOException e) {
            throw new ConnectionFailedException("An error occurred connecting to crypto currency converter service! " +
                    "Unable to establish a connection!", e);
        }
    }

    private BigDecimal readConversionRateFromResponse(HttpURLConnection connection) {
        int responseCode = getResponseCode(connection);
        if (responseCode == 200) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String responseBody = bufferedReader.readLine();
                Pattern pattern = Pattern.compile(CRYPTOCURRENCY_TO_CURRENCY_RESPONSE_BODY_FORMAT);
                Matcher matcher = pattern.matcher(responseBody);
                if (matcher.find()) {
                    return BigDecimal.valueOf(Double.parseDouble(matcher.group(1)));
                }
            } catch (Exception e) {
                throw new InvalidCryptoCurrencyResponseException("An error occurred reading crypto currency converter response!", e);
            }
        }

        throw new InvalidCryptoCurrencyResponseException(format("Invalid crypto currency converter response! " +
                "Response code: %d", responseCode));
    }

    private int getResponseCode(HttpURLConnection connection) {
        int responseCode;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new ConnectionFailedException("An error occurred connecting to crypto currency converter service! " +
                    "Unable to read response code!", e);
        }

        return responseCode;
    }

    private URL getUrl(String cryptoCurrency, String currency) {
        try {
            return new URL(format(CRYPTOCURRENCY_TO_CURRENCY_URL_FORMAT,
                    CRYPTOCURRENCY_QUERY_PARAM,
                    cryptoCurrency,
                    CURRENCY_PARAM,
                    currency));
        } catch (MalformedURLException e) {
            throw new InvalidUrlException("Crypto currency conversion URL is invalid!", e);
        }
    }
}
