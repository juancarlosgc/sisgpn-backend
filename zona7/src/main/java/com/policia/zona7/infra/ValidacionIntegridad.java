package com.policia.zona7.infra;

public class ValidacionIntegridad extends RuntimeException {
    public ValidacionIntegridad(String s) {
        super(s);
    }
}
