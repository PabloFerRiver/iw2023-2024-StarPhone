package com.application.user.unit.contract;

import com.application.Contract.Entities.Bill;
import com.application.Contract.Entities.Contract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillTest {

    private Bill bill;

    @BeforeEach
    public void setUp() {
        bill = new Bill();
    }

    @Test
    public void testId() {
        UUID id = UUID.randomUUID();
        bill.setId(id);
        assertEquals(id, bill.getId());
    }

    @Test
    public void testContract() {
        Contract contract = new Contract();
        bill.setContract(contract);
        assertEquals(contract, bill.getContract());
    }

    @Test
    public void testAmount() {
        double amount = 100.0;
        bill.setAmount(amount);
        assertEquals(amount, bill.getAmount());
    }

    @Test
    public void testPaymentStatus() {
        String paymentStatus = "Paid";
        bill.setPaymentStatus(paymentStatus);
        assertEquals(paymentStatus, bill.getPaymentStatus());
    }

    @Test
    public void testPDFBill() {
        byte[] pdfBill = new byte[]{1, 2, 3};
        bill.setPDFBill(pdfBill);
        assertEquals(pdfBill, bill.getPDFBill());
    }

    @Test
    public void testStartDate() {
        LocalDate startDate = LocalDate.now();
        bill.setStartDate(startDate);
        assertEquals(startDate, bill.getStartDate());
    }

    @Test
    public void testEndDate() {
        LocalDate endDate = LocalDate.now();
        bill.setEndDate(endDate);
        assertEquals(endDate, bill.getEndDate());
    }
}