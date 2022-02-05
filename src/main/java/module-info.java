module dev.nicklasw.bankid {
    requires static lombok;
    requires static com.github.spotbugs.annotations;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    exports dev.nicklasw.bankid;
    exports dev.nicklasw.bankid.configuration;
    exports dev.nicklasw.bankid.client;
    exports dev.nicklasw.bankid.exceptions;
    exports dev.nicklasw.bankid.client.response;
    exports dev.nicklasw.bankid.client.request;
    exports dev.nicklasw.bankid.client.model;
    exports dev.nicklasw.bankid.client.utils;
}