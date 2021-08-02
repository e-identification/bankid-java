package dev.nicklasw.bankid.configuration;

import java.nio.file.Path;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Configuration {
    public static final String URL_PRODUCTION = "https://appapi2.bankid.com/rp/v5.1/";
    public static final String URL_TEST = "https://appapi2.test.bankid.com/rp/v5.1/";

    private final String baseURL;
    private final Pkcs12 pkcs12;
    private final Path certificate;
}
