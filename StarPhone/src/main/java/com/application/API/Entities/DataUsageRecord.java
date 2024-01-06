package com.application.API.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "datausagerecord")
public class DataUsageRecord {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @Column(name= "megaBytes") // sera del tipo ($int32)
    private int megaBytes;

    @Column(name= "date") // sera del tipo ($date)
    private String date;

    @ManyToOne
    @JoinColumn(name = "customerline_id")
    private CustomerLine customerLine;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getMegaBytes() {
        return megaBytes;
    }

    public void setMegaBytes(int megaBytes) {
        this.megaBytes = megaBytes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        DataUsageRecord that = (DataUsageRecord) o;
        return megaBytes == that.megaBytes && Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(customerLine, that.customerLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
