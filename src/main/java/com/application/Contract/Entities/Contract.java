package com.application.Contract.Entities;

import com.application.General.AbstractEntity;
import com.application.MobileLine.Entities.MobileLine;
import com.application.User.Entities.User;
import com.application.MobileLine.Entities.Fee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contract", indexes = {
        @Index(name = "id_userfeestatus", columnList = "user_id, fee_id, status", unique = true),
})
public class Contract extends AbstractEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fee_id")
    @NotNull
    private Fee fee;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusContract status;

    @NotNull
    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contract")
    private List<MobileLine> mobileLines;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contract")
    private List<Bill> bills;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contract")
    private List<QueryComplaint> queryComplaints;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public StatusContract getStatus() {
        return status;
    }

    public void setStatus(StatusContract status) {
        this.status = status;
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

    public List<MobileLine> getMobileLines() {
        return mobileLines;
    }

    public void setMobileLines(List<MobileLine> mobileLines) {
        this.mobileLines = mobileLines;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setFacturas(List<Bill> bills) {
        this.bills = bills;
    }

    public List<QueryComplaint> getQueryComplaints() {
        return queryComplaints;
    }

    public void setQueryComplaints(List<QueryComplaint> queryComplaints) {
        this.queryComplaints = queryComplaints;
    }
}