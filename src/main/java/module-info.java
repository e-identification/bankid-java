module dev.nicklasw.bankid {
    requires static lombok;
    requires static com.github.spotbugs.annotations;

    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires dev.mccue.jsr305;

    exports dev.nicklasw.bankid;

    exports dev.nicklasw.bankid.client.response;
    exports dev.nicklasw.bankid.client.request;
    exports dev.nicklasw.bankid.client.model;
    exports dev.nicklasw.bankid.client.model.serializer;
    exports dev.nicklasw.bankid.client.utils;

    exports dev.nicklasw.bankid.configuration;
    exports dev.nicklasw.bankid.exceptions;
    exports dev.nicklasw.bankid.internal.http;

    opens dev.nicklasw.bankid.client.model.serializer to com.fasterxml.jackson.databind;
    opens dev.nicklasw.bankid.client.response to com.fasterxml.jackson.databind;
}