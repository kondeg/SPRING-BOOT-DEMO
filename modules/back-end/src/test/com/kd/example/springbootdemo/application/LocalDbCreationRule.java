package com.kd.example.springbootdemo.application;

import org.junit.rules.ExternalResource;

import java.util.Optional;

public class LocalDbCreationRule extends ExternalResource {

    public LocalDbCreationRule() {
        System.setProperty("sqlite4java.library.path", "native-libs");
    }

}