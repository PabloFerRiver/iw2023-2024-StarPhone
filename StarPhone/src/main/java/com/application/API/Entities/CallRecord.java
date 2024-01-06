package com.application.API.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "callrecord")
public class CallRecord  {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @Column(name= "destinationPhoneNumber") // sera del tipo (String)
    private String destinationPhoneNumber;

    @Column(name= "seconds") // sera del tipo integer($int32)
    private int seconds;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Column(name= "dateTime") // sera del tipo string($date-time)
    private ZonedDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "customerline_id")
    private CustomerLine customerLine;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDestinationPhoneNumber() {
        return destinationPhoneNumber;
    }

    public void setDestinationPhoneNumber(String destinationPhoneNumber) {
        this.destinationPhoneNumber = destinationPhoneNumber;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public CustomerLine getCustomerLine() {
        return customerLine;
    }

    public void setCustomerLine(CustomerLine customerLine) {
        this.customerLine = customerLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallRecord that = (CallRecord) o;
        return seconds == that.seconds && Objects.equals(id, that.id) && Objects.equals(destinationPhoneNumber, that.destinationPhoneNumber) && Objects.equals(dateTime, that.dateTime) && Objects.equals(customerLine, that.customerLine);
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
