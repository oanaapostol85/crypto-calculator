package com.swissre.cryptocalculator;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class FilePath {

    public static String getFilePathFromResources(String fileName) {

        URL resource = FilePath.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File is not found!");
        }

        try {
            return Paths.get(resource.toURI()).toString();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("File URL is invalid!");
        }
    }
}
