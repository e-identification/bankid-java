package dev.nicklasw.bankid.client.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class User {
    String personalNumber;
    String name;
    String givenName;
    String surname;
}
