package com.swissre.cryptocalculator;

import java.util.Scanner;

public class CryptoWalletApplication {

    public static void main(String[] args) {
        System.out.println("Welcome to Crypto Calculator!");

        Scanner scanner = new Scanner(System.in);
        String cryptoWalletFilePath = readCryptoWalletFilePath(scanner);
        String currency = readCurrency(scanner);

        new CryptoWalletCalculatorBuilder()
                .withCryptoWalletFilePath(cryptoWalletFilePath)
                .withCurrency(currency)
                .build()
                .calculate()
                .prettyPrint();
    }

    private static String readCryptoWalletFilePath(Scanner scanner) {
        System.out.print("Enter the path of the crypto wallet file: ");
        return scanner.next();
    }

    private static String readCurrency(Scanner scanner) {
        System.out.print("Enter conversion currency (e.g. EUR): ");
        return scanner.next();
    }
}
