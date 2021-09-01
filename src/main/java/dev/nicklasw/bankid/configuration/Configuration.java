package dev.nicklasw.bankid.configuration;

import java.nio.file.Path;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Configuration {
    public static final String URL_PRODUCTION = "https://appapi2.bankid.com/rp/v5.1/";
    public static final String URL_TEST = "https://appapi2.test.bankid.com/rp/v5.1/";

    private final String baseURL;
    private final Pkcs12 pkcs12;
    private final Path certificate;
}
