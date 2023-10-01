package dev.nicklasw.bankid.client.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardReader {
    /**
     * The transaction must be performed using a card reader where the PIN code is entered on a computer keyboard, or a card reader of higher class.
     */
    CLASS1("class1"),
    /**
     * The transaction must be performed using a card reader where the PIN code is entered on the reader, or a reader of higher class.
     */
    CLASS2("class2");

    private final String code;
}
