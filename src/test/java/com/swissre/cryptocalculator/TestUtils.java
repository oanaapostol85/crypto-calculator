package com.swissre.cryptocalculator;

import java.net.URL;

public class TestUtils {

    public static String getFilePathFromResources(String fileName) {

        URL resource = TestUtils.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File is not found!");
        }

        return resource.getFile();
    }
}
