package com.application.MobileLine.Entities;

import com.application.General.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "dataUsageRecords", indexes = {
        @Index(name = "id_dataUsageRecords", columnList = "id", unique = true)
})
public class DataUsageRecords extends AbstractEntity {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mobileLine_id", nullable = false)
    private MobileLine mobileLine;

    @NotNull
    @Column(name = "megas", nullable = false)
    private double megas;

    @NotNull
    @Column(name = "dataUsageDate", nullable = false)
    private LocalDate dataUsageDate;

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

    public double getMegas() {
        return this.megas;
    }

    public void setMegas(double megas) {
        this.megas = megas;
    }

    public LocalDate getDataUsageDate() {
        return this.dataUsageDate;
    }

    public void setDataDate(LocalDate dataUsageDate) {
        this.dataUsageDate = dataUsageDate;
    }
}