import org.jspecify.annotations.NullMarked;

/**
 * The module "dev.eidentification.bankid" is responsible for managing integrations with the BankID service.
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
module dev.eidentification.bankid {
    requires static org.jspecify;

    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports dev.eidentification.bankid;

    exports dev.eidentification.bankid.client.response;
    exports dev.eidentification.bankid.client.request;
    exports dev.eidentification.bankid.client.model;
    exports dev.eidentification.bankid.client.model.enums;
    exports dev.eidentification.bankid.client.model.serializer;
    exports dev.eidentification.bankid.client.utils;

    exports dev.eidentification.bankid.configuration;
    exports dev.eidentification.bankid.exceptions;

    opens dev.eidentification.bankid.client.model.serializer to com.fasterxml.jackson.databind;
    opens dev.eidentification.bankid.client.response to com.fasterxml.jackson.databind;
}