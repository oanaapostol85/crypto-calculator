package com.swissre.cryptocalculator;

import com.swissre.cryptocalculator.converter.CryptoCurrencyConverter;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class CryptoWalletCalculator {

    private String cryptoWalletFilePath;

    private String currency;

    private CryptoCurrencyConverter cryptoCurrencyConverter;

    private Map<String, BigDecimal> cryptoCurrencyAmtMap;

    private Map<String, BigDecimal> cryptoCurrencyConvertedAmtMap;

    private BigDecimal totalAmt;

    public CryptoWalletCalculator calculate() {
        cryptoCurrencyAmtMap = new CryptoWalletFileReader().readFile(cryptoWalletFilePath);

        cryptoCurrencyConvertedAmtMap = convert(cryptoCurrencyAmtMap, currency);
        totalAmt = calculateTotalAmt(cryptoCurrencyConvertedAmtMap);

        return this;
    }

    public void prettyPrint() {
        cryptoCurrencyConvertedAmtMap
                .forEach((cryptoCurrency, price) ->
                        System.out.println(format("%.2f %s=%.2f %s", cryptoCurrencyAmtMap.get(cryptoCurrency), cryptoCurrency, price, currency)));

        System.out.println(format("Total=%.2f %s", totalAmt, currency));
    }

    private Map<String, BigDecimal> convert(Map<String, BigDecimal> cryptoCurrencyAmtMap, String currency) {
        return cryptoCurrencyAmtMap.entrySet()
                .parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        cryptoCurrencyAmtEntry -> convert(cryptoCurrencyAmtEntry.getKey(),
                                cryptoCurrencyAmtEntry.getValue(),
                                currency)));
    }

    private BigDecimal convert(String cryptoCurrency, BigDecimal cryptoCurrencyAmount, String currency) {
        return cryptoCurrencyConverter.convert(cryptoCurrency, cryptoCurrencyAmount, currency);
    }

    private BigDecimal calculateTotalAmt(Map<String, BigDecimal> cryptoCurrencyConvertedAmtMap) {
        return cryptoCurrencyConvertedAmtMap.values()
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setCryptoWalletFilePath(String cryptoWalletFilePath) {
        this.cryptoWalletFilePath = cryptoWalletFilePath;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCryptoCurrencyConverter(CryptoCurrencyConverter cryptoCurrencyConverter) {
        this.cryptoCurrencyConverter = cryptoCurrencyConverter;
    }

    public Map<String, BigDecimal> getCryptoCurrencyConvertedAmtMap() {
        return cryptoCurrencyConvertedAmtMap;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }
}
