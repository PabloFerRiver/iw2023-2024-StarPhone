package com.application.MobileLine.Entities;

import com.application.General.AbstractEntity;
import com.application.Contract.Entities.Contract;
import com.application.User.Entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mobileline", indexes = {
        @Index(name = "id_contract", columnList = "contract_id", unique = false),
        @Index(name = "mobLinePhoneNum", columnList = "phoneNumber", unique = true)
})
public class MobileLine extends AbstractEntity {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @NotNull
    @Column(name = "phoneNumber")
    private Integer phoneNumber;

    @Column(name = "roaming", nullable = false)
    private boolean roaming;

    @Column(name = "shareData", nullable = false)
    private boolean shareData;

    @Column(name = "totalDataToday", nullable = false)
    private double totalDataToday;

    @Column(name = "totalCallsToday", nullable = false)
    private int totalCallsToday;

    @Column(name = "totalSMSToday", nullable = false)
    private int totalSMSToday;

    @Column(name = "totalDataMonth", nullable = false)
    private double totalDataMonth;

    @Column(name = "totalCallsMonth", nullable = false)
    private int totalCallsMonth;

    @Column(name = "totalSMSMonth", nullable = false)
    private int totalSMSMonth;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "mobileLine_id")
    private List<BlockedNumbers> blockedNumbers = new ArrayList<>();

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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isRoaming() {
        return roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }

    public boolean getRoaming() {
        return roaming;
    }

    public boolean getShareData() {
        return shareData;
    }

    public void setShareData(boolean shareData) {
        this.shareData = shareData;
    }

    public Double getTotalDataToday() {
        return totalDataToday;
    }

    public void setTotalDataToday(Double totalDataToday) {
        this.totalDataToday = totalDataToday;
    }

    public void addTotalDataToday(Double totalDataToday) {
        this.totalDataToday += totalDataToday;
    }

    public int getTotalCallsToday() {
        return totalCallsToday;
    }

    public void setTotalCallsToday(int totalcallsToday) {
        this.totalCallsToday = totalcallsToday;
    }

    public void addTotalCallsToday(int totalCallsToday) {
        this.totalCallsToday += totalCallsToday;
    }

    public int getTotalSMSToday() {
        return totalSMSToday;
    }

    public void setTotalSMSToday(int totalSMSToday) {
        this.totalSMSToday = totalSMSToday;
    }

    public void addTotalSMSToday(int totalSMSToday) {
        this.totalSMSToday += totalSMSToday;
    }

    public Double getTotalDataMonth() {
        return totalDataMonth;
    }

    public void setTotalDataMonth(Double totalDataMonth) {
        this.totalDataMonth = totalDataMonth;
    }

    public void addTotalDataMonth(Double totalDataMonth) {
        this.totalDataMonth += totalDataMonth;
    }

    public int getTotalCallsMonth() {
        return totalCallsMonth;
    }

    public void setTotalCallsMonth(int totalcallsMonth) {
        this.totalCallsMonth = totalcallsMonth;
    }

    public void addTotalCallsMonth(int totalCallsMonth) {
        this.totalCallsMonth += totalCallsMonth;
    }

    public int getTotalSMSMonth() {
        return totalSMSMonth;
    }

    public void setTotalSMSMonth(int totalSMSMonth) {
        this.totalSMSMonth = totalSMSMonth;
    }

    public void addTotalSMSMonth(int totalSMSMonth) {
        this.totalSMSMonth += totalSMSMonth;
    }

    public List<BlockedNumbers> getBlockedNumbers() {
        return this.blockedNumbers;
    }

    public void setBlockedNumbers(List<BlockedNumbers> blockedNumbers) {
        this.blockedNumbers = blockedNumbers;
    }

    public void addBlockedNumber(BlockedNumbers blockedNumbers) {
        this.blockedNumbers.add(blockedNumbers);
    }

}
