package com.application.MobileLine.Entities;

import com.application.General.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "callRecords", indexes = {
        @Index(name = "id_callRecords", columnList = "id", unique = true)
})
public class CallRecords extends AbstractEntity {

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

    @NotNull
    @Column(name = "seconds", nullable = false)
    private double seconds;

    @NotNull
    @Column(name = "callDate", nullable = false)
    private LocalDate callDate;

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

    public double getSeconds() {
        return this.seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }

    public LocalDate getCallDate() {
        return this.callDate;
    }

    public void setCallDate(LocalDate callDate) {
        this.callDate = callDate;
    }
}
