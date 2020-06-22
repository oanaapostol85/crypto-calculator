package com.swissre.cryptocalculator;

import java.util.Scanner;

public class CryptoWalletApplication {

    public static final String EUR = "EUR";

    public static void main(String[] args) {
        System.out.println("Welcome to Crypto Calculator!");

        Scanner scanner = new Scanner(System.in);
        String cryptoWalletFilePath = readCryptoWalletFilePath(scanner);

        new CryptoWalletCalculatorBuilder()
                .withCryptoWalletFilePath(cryptoWalletFilePath)
                .withCurrency(EUR)
                .build()
                .calculate()
                .prettyPrint();
    }

    private static String readCryptoWalletFilePath(Scanner scanner) {
        System.out.print("Enter the path of the crypto wallet file: ");
        return scanner.next();
    }
}
