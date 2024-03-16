package dev.eidentification.bankid.client.utils;

import dev.eidentification.bankid.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QRCodeUtilsUnitTest extends UnitTest {

    @Test
    void qrCode() {
        var result = QRCodeUtils.qrCode("67df3917-fa0d-44e5-b327-edcc928297f8", "d28db9a7-4cde-429e-a983-359be676944c", LocalDateTime.now());

        Assertions.assertEquals("bankid.67df3917-fa0d-44e5-b327-edcc928297f8.0.dc69358e712458a66a7525beef148ae8526b1c71610eff2c16cdffb4cdac9bf8", result);
    }

    @Test
    public void qrCodeWithNullPointerException() {
        String qrStartToken = "startTokenTest";
        String qrStartSecret = null; // set to null or any values that lead to exception
        LocalDateTime orderTime = LocalDateTime.of(2023, 3, 4, 15, 30);

        assertThrows(NullPointerException.class, () -> QRCodeUtils.qrCode(qrStartToken, qrStartSecret, orderTime));
    }

}