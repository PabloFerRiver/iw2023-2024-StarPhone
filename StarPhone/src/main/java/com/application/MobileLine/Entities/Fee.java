package com.application.MobileLine.Entities;

import com.application.General.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "fee", indexes = {
        @Index(name = "id_fee", columnList = "id", unique = false)
})
public class Fee extends AbstractEntity {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @NotEmpty
    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @NotEmpty
    @Column(name = "descriptionMobile", nullable = false)
    private String descriptionMobile;

    @NotEmpty
    @Column(name = "descriptionFiber", nullable = false)
    private String descriptionFiber;

    @NotEmpty
    @Column(name = "descriptionTV", nullable = false)
    private String descriptionTV;

    @NotNull
    @Column(name = "monthlyData", nullable = false)
    private Double monthlyData;

    @NotNull
    @Column(name = "monthlyCalls", nullable = false)
    private int monthlyCalls;

    @NotNull
    @Column(name = "monthlySMS", nullable = false)
    private int monthlySMS;

    @NotNull
    @Column(name = "monthlyprice", nullable = false)
    private double monthlyprice;

    @NotNull
    @Column(name = "maxMobileLines", nullable = false)
    private int maxMobileLines;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionMobile() {
        return descriptionMobile;
    }

    public void setDescriptionMobile(String descriptionMobile) {
        this.descriptionMobile = descriptionMobile;
    }

    public String getDescriptionFiber() {
        return descriptionFiber;
    }

    public void setDescriptionFiber(String descriptionFiber) {
        this.descriptionFiber = descriptionFiber;
    }

    public String getDescriptionTV() {
        return descriptionTV;
    }

    public void setDescriptionTV(String descriptionTV) {
        this.descriptionTV = descriptionTV;
    }

    public Double getMonthlyData() {
        return this.monthlyData;
    }

    public void setMonthlyData(Double monthlyData) {
        this.monthlyData = monthlyData;
    }

    public int getMonthlyCalls() {
        return this.monthlyCalls;
    }

    public void setMonthlyCalls(int monthlyCalls) {
        this.monthlyCalls = monthlyCalls;
    }

    public int getMonthlySMS() {
        return this.monthlySMS;
    }

    public void setMonthlySMS(int monthlySMS) {
        this.monthlySMS = monthlySMS;
    }

    public double getMonthlyprice() {
        return this.monthlyprice;
    }

    public void setMonthlyprice(double monthlyprice) {
        this.monthlyprice = monthlyprice;
    }

    public int getMaxMobileLines() {
        return this.maxMobileLines;
    }

    public void setMaxMobileLines(int maxMobileLines) {
        this.maxMobileLines = maxMobileLines;
    }
}