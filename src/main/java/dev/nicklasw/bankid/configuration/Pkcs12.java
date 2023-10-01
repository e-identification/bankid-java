package dev.nicklasw.bankid.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.io.InputStream;

@Value
@Builder
@AllArgsConstructor(staticName = "of")
public class Pkcs12 {
    @NonNull
    InputStream path;
    @NonNull
    String password;
}
