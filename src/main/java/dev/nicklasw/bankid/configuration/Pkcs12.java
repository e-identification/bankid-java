package dev.nicklasw.bankid.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.nio.file.Path;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public class Pkcs12 {
    @NonNull
    private final Path path;
    @NonNull
    private final String password;
}
