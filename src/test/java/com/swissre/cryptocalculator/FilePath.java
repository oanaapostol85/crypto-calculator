package com.swissre.cryptocalculator;

import java.net.URL;

public class FilePath {

    public static String getFilePathFromResources(String fileName) {

        URL resource = FilePath.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File is not found!");
        }

        return resource.getFile();
    }
}
