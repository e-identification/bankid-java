[![Continuous Integration](https://github.com/nicklaswallgren/bankid-java-sdk/workflows/ci/badge.svg)](https://github.com/nicklaswallgren/bankid-java-sdk/actions)
[![License](https://img.shields.io/github/license/nicklaswallgren/bankid-java-sdk)](https://github.com/nicklaswallgren/bankid-java-sdk/blob/master/LICENSE)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/NicklasWallgren/bankid-java-sdk.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/NicklasWallgren/bankid-java-sdk/context:java)

# BankID SDK

SDK to interact with BankID API. It includes support for all the v5.1 features. There are
some [examples that may be useful](./examples).

## Installation

The artifact is available through Maven Central via Sonatype.

### Maven

```xml

<dependency>
    <groupId>dev.nicklasw</groupId>
    <artifactId>bankid-sdk</artifactId>
    <version>0.10.0</version>
</dependency>
```

### Gradle

```
implementation 'dev.nicklasw:bankid-sdk:0.10.0'
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
import static dev.nicklasw.bankid.configuration.Configuration.URL_TEST;

import java.net.URI;
import java.nio.file.Path;

import dev.nicklasw.bankid.BankId;
import dev.nicklasw.bankid.client.request.AuthenticationRequest;
import dev.nicklasw.bankid.client.response.AuthenticateResponse;
import dev.nicklasw.bankid.client.utils.ResourceUtils;
import dev.nicklasw.bankid.configuration.Configuration;
import dev.nicklasw.bankid.configuration.Pkcs12;
import dev.nicklasw.bankid.exceptions.BankIdApiErrorException;

final URI pkcs12ResourceUri = ResourceUtils.optionalUriFrom("test.p12")
    .orElseThrow();
final URI caResourceUri = ResourceUtils.optionalUriFrom("ca.test.crt")
    .orElseThrow();

final Configuration configuration = Configuration.builder()
    .baseURL(URL_TEST)
    .pkcs12(Pkcs12.of(Path.of(pkcs12ResourceUri), "qwerty123"))
    .certificate(Path.of(caResourceUri))
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
