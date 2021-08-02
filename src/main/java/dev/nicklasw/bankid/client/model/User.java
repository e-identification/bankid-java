package dev.nicklasw.bankid.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode
public class User {

    private final String personalNumber;
    private final String name;
    private final String givenName;
    private final String surname;

}
