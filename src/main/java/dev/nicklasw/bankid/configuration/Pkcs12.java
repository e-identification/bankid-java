package dev.nicklasw.bankid.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class Pkcs12 {
    private final Path path;
    private final String password;
}
