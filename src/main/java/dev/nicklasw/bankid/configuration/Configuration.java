package dev.nicklasw.bankid.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.io.InputStream;

@Value
@Builder
@AllArgsConstructor(staticName = "of")
public class Configuration {
    public static final String URL_PRODUCTION = "https://appapi2.bankid.com/rp/v6.0/";
    public static final String URL_TEST = "https://appapi2.test.bankid.com/rp/v6.0/";

    @NonNull
    String baseURL;
    @NonNull
    Pkcs12 pkcs12;
    @NonNull
    InputStream certificate;
}
