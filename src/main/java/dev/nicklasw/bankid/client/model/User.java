package dev.nicklasw.bankid.client.model;

public record User(
    String personalNumber,
    String name,
    String givenName,
    String surname) {
}
