package com.application.API.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customerline")
public class CustomerLine {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "carrier")
    private String carrier;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "customerline_id")
    private List<DataUsageRecord> dataUsageRecord = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "customerline_id")
    private List<CallRecord> callRecords = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<DataUsageRecord> getDataUsageRecord() {
        return dataUsageRecord;
    }

    public void setDataUsageRecord(List<DataUsageRecord> dataUsageRecord) {
        this.dataUsageRecord = dataUsageRecord;
    }

    public List<CallRecord> getCallRecords() {
        return callRecords;
    }

    public void setCallRecords(List<CallRecord> callRecords) {
        this.callRecords = callRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CustomerLine that = (CustomerLine) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname)
                && Objects.equals(carrier, that.carrier) && Objects.equals(phoneNumber, that.phoneNumber)
                && Objects.equals(dataUsageRecord, that.dataUsageRecord)
                && Objects.equals(callRecords, that.callRecords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
