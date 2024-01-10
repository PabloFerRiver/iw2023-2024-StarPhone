package com.application.Contract.Entities;

import com.application.General.AbstractEntity;
import com.vaadin.flow.component.template.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Bill", indexes = {
        @Index(name = "id_contract", columnList = "contract_id", unique = false)
})

public class Bill extends AbstractEntity {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @NotEmpty
    @Column(name = "amount")
    private double amount;

    @NotEmpty
    @Column(name = "paymentStatus")
    private String paymentStatus;

    // Guardar un pdf
    @NotEmpty
    @Column(name = "PDF_Bill", columnDefinition = "BLOB")
    @Lob
    private byte[] PDFBill;

    @NotEmpty
    @Column(name = "startDate")
    private LocalDate startDate;

    @NotEmpty
    @Column(name = "endDate")
    private LocalDate endDate;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public byte[] getPDFBill() {
        return PDFBill;
    }

    public void setPDFBill(byte[] PDFBill) {
        this.PDFBill = PDFBill;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
