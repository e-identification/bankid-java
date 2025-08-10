[![Continuous Integration](https://github.com/e-identification/bankid-java/workflows/ci/badge.svg)](https://github.com/e-identification/bankid-java/actions)
[![License](https://img.shields.io/github/license/e-identification/bankid-java)](https://github.com/e-identification/bankid-java/blob/master/LICENSE)

# BankID SDK

SDK to interact with BankID API. It includes support for all the v6.0 features. There are
some [examples that may be useful](./examples).

## Documentation
See the project's [Javadoc](https://e-identification.github.io/bankid-java/).

## Installation

The artifact is available through Maven Central via Sonatype.

### Maven

```xml

<dependency>
    <groupId>dev.eidentification</groupId>
    <artifactId>bankid-sdk</artifactId>
    <version>0.16.0</version>
</dependency>
```

### Gradle

```
implementation 'dev.eidentification:bankid-sdk:0.16.0'
```

## Changelog

Please see the [changelog](./CHANGELOG.md) for a release history and indications on how to upgrade from one version to
another.

## Contributing

If you find any problems or have suggestions about this library, please submit an issue. Moreover, any pull request,
code review and feedback are welcome.

## Code Guide

We use GitHub Actions to make sure the codebase is consistent and continuously tested (`gradle check`). We try to keep
comments at a maximum of 120 characters of length and code at 120.

## General Usage

```java 
import static dev.eidentification.configuration.bankid.Configuration.URL_TEST;

import dev.eidentification.bankid.BankId;
import dev.eidentification.request.client.bankid.AuthenticationRequest;
import dev.eidentification.response.client.bankid.AuthenticateResponse;
import dev.eidentification.utils.client.bankid.ResourceUtils;
import dev.eidentification.configuration.bankid.Configuration;
import dev.eidentification.configuration.bankid.Pkcs12;
import dev.eidentification.exceptions.bankid.BankIdApiErrorException;

final InputStream pkcs12Resource = ResourceUtils.tryInputStreamFrom("test.p12");
final InputStream caResource = ResourceUtils.tryInputStreamFrom("ca.test.crt");

final Configuration configuration = Configuration.builder()
    .baseURL(URL_TEST)
    .pkcs12(Pkcs12.of(pkcs12Resource, "qwerty123"))
    .certificate(caResource)
    .build();

final BankId bankId = BankId.of(configuration);

final AuthenticateResponse authenticateResponse = bankId.authenticate(
    AuthenticationRequest.builder()
        .personalNumber("PERSONAL_NUMBER")
        .endUserIp("IP_ADDRESS")
        .build());
```

## License

[MIT](./LICENSE)
