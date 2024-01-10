package com.application.user.unit.contract;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.application.Contract.Entities.Bill;
import com.application.Contract.Entities.Contract;
import com.application.Contract.Entities.QueryComplaint;
import com.application.MobileLine.Entities.Fee;
import com.application.MobileLine.Entities.MobileLine;
import com.application.User.Entities.User;

public class ContractTest {
    private Contract contract;

    @Before
    public void setUp() {
        contract = new Contract();
    }

    @Test
    public void testId() {
        UUID id = UUID.randomUUID();
        contract.setId(id);
        assertEquals(id, contract.getId());
    }

    @Test
    public void testUser() {
        User user = new User();
        contract.setUser(user);
        assertEquals(user, contract.getUser());
    }

    @Test
    public void testFee() {
        Fee fee = new Fee();
        contract.setFee(fee);
        assertEquals(fee, contract.getFee());
    }

    @Test
    public void testStatus() {
        String status = "active";
        contract.setStatus(status);
        assertEquals(status, contract.getStatus());
    }

    @Test
    public void testStartDate() {
        LocalDate startDate = LocalDate.now();
        contract.setStartDate(startDate);
        assertEquals(startDate, contract.getStartDate());
    }

    @Test
    public void testEndDate() {
        LocalDate endDate = LocalDate.now();
        contract.setEndDate(endDate);
        assertEquals(endDate, contract.getEndDate());
    }

    @Test
    public void testMobileLines() {
        MobileLine mobileLine = new MobileLine();
        contract.setMobileLines(Arrays.asList(mobileLine));
        assertEquals(mobileLine, contract.getMobileLines().get(0));
    }

    @Test
    public void testBills() {
        Bill bill = new Bill();
        contract.setFacturas(Arrays.asList(bill));
        assertEquals(bill, contract.getBills().get(0));
    }

    @Test
    public void testQueryComplaints() {
        QueryComplaint queryComplaint = new QueryComplaint();
        contract.setQueryComplaints(Arrays.asList(queryComplaint));
        assertEquals(queryComplaint, contract.getQueryComplaints().get(0));
    }
}