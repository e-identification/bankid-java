import org.jspecify.annotations.NullMarked;

/**
 * The module "dev.nicklasw.bankid" is responsible for managing integrations with the BankID service.
 * <p>
 * This module exports several packages that contain classes related to BankID integration,
 * including client response models, client request models, client utility classes,
 * configuration classes, and exception classes.
 * <p>
 * The module declares dependencies on other modules, which are:
 * - org.jspecify
 * - java.net.http
 * - com.fasterxml.jackson.annotation
 * - com.fasterxml.jackson.core
 * - com.fasterxml.jackson.databind
 * <p>
 * This module is annotated with @NullMarked, indicating that it leverages nullability annotations,
 * providing information about nullability in the exported types.
 **/
@NullMarked
module dev.nicklasw.bankid {
    requires static org.jspecify;

    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports dev.nicklasw.bankid;

    exports dev.nicklasw.bankid.client.response;
    exports dev.nicklasw.bankid.client.request;
    exports dev.nicklasw.bankid.client.model;
    exports dev.nicklasw.bankid.client.model.enums;
    exports dev.nicklasw.bankid.client.model.serializer;
    exports dev.nicklasw.bankid.client.utils;

    exports dev.nicklasw.bankid.configuration;
    exports dev.nicklasw.bankid.exceptions;

    opens dev.nicklasw.bankid.client.model.serializer to com.fasterxml.jackson.databind;
    opens dev.nicklasw.bankid.client.response to com.fasterxml.jackson.databind;
}