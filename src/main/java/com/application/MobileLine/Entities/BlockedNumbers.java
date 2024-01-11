package com.application.MobileLine.Entities;

import com.application.General.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "blockedNumbers", indexes = {
        @Index(name = "blockedNumberMobileLine", columnList = "blockedNumber, mobileLineId", unique = true)
})

public class BlockedNumbers extends AbstractEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "blockedNumber", nullable = false)
    private Integer blockedNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mobileLine_id", nullable = false)
    private MobileLine mobileLine;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getBlockedNumber() {
        return blockedNumber;
    }

    public void setBlockedNumber(Integer blockedNumber) {
        this.blockedNumber = blockedNumber;
    }

    public MobileLine getMobileLine() {
        return mobileLine;
    }

    public void setMobileLine(MobileLine mobileLine) {
        this.mobileLine = mobileLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BlockedNumbers that = (BlockedNumbers) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
