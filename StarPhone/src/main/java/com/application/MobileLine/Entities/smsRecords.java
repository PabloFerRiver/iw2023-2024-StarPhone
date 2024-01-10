package com.application.MobileLine.Entities;

import com.application.General.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "smsRecords", indexes = {
        @Index(name = "id_smsRecords", columnList = "id", unique = true)
})
public class SmsRecords extends AbstractEntity {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mobileLine_id", nullable = false)
    private MobileLine mobileLine;

    @NotNull
    @Column(name = "destinationPhoneNumber", nullable = false)
    private Integer destinationPhoneNumber;

    @NotEmpty
    @Column(name = "smsText", nullable = false)
    private String smsText;

    @NotNull
    @Column(name = "smsDate", nullable = false)
    private LocalDate smsDate;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MobileLine getMobileLine() {
        return this.mobileLine;
    }

    public void setMobileLine(MobileLine mobileLine) {
        this.mobileLine = mobileLine;
    }

    public Integer getDestinationPhoneNumber() {
        return this.destinationPhoneNumber;
    }

    public void setDestinationPhoneNumber(Integer destinationPhoneNumber) {
        this.destinationPhoneNumber = destinationPhoneNumber;
    }

    public String getSmsText() {
        return this.smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public LocalDate getSmsDate() {
        return this.smsDate;
    }

    public void setSmsDate(LocalDate smsDate) {
        this.smsDate = smsDate;
    }
}
