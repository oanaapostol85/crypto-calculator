package com.swissre.cryptocalculator.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MockHttpURLConnection extends HttpURLConnection {

    private final Integer responseCode;

    private final String responseBody;

    public MockHttpURLConnection(Integer responseCode, String responseBody) {
        super(null);
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }

    @Override
    public void disconnect() {
        // Not implemented
    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public void connect() {
        // Not implemented
    }

    @Override
    public int getResponseCode() throws IOException {
        return responseCode;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(responseBody.getBytes(UTF_8));
    }
}
