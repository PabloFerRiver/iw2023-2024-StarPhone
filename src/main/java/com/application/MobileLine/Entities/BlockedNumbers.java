package com.application.MobileLine.Entities;

import com.application.General.AbstractEntity;
import jakarta.persistence.*;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "blockedNumbers", indexes = {
        @Index(name = "blockedNumberMobileLine", columnList = "blocked_number, mobile_line_id", unique = true)
})

public class BlockedNumbers extends AbstractEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @Column(name = "blockedNumber")
    private Integer blockedNumber;

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
}
