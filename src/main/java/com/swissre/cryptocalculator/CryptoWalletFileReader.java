package com.swissre.cryptocalculator;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CryptoWalletFileReader {

    private static final Pattern DELIMITER = Pattern.compile("=");

    public Map<String, BigDecimal> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath))
                    .stream()
                    .filter(line -> line != null && !line.isEmpty())
                    .map(line -> line.replaceAll("\\s", ""))
                    .map(DELIMITER::split)
                    .collect(Collectors.toMap(lineTokens -> lineTokens[0],
                            lineTokens -> new BigDecimal(lineTokens[1])));
        } catch (IOException e) {
            System.err.println("Unable to read crypto wallet file!");
            e.printStackTrace();
        }

        return Collections.emptyMap();
    }
}
