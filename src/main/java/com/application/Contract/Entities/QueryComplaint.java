package com.application.Contract.Entities;

import com.application.General.AbstractEntity;
import com.application.MobileLine.Entities.MobileLine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "QueryComplaint")
public class QueryComplaint extends AbstractEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mobileLine_id")
    private MobileLine mobileLine;

    @NotEmpty
    @Column(name = "description")
    private String description;

    // Estado
    @NotEmpty
    @Column(name = "status")
    private String status;

    // Fecha Creacion
    @NotEmpty
    @Column(name = "createDate")
    private LocalDate createDate;

    @NotEmpty
    @Column(name = "resolutionDate")
    private LocalDate resolutionDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "queryComplaint_id")
    private List<ResponseComplaint> responseComplaints;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public MobileLine getMobileLine() {
        return mobileLine;
    }

    public void setMobileLine(MobileLine mobileLine) {
        this.mobileLine = mobileLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(LocalDate resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public List<ResponseComplaint> getResponseComplaints() {
        return this.responseComplaints;
    }

    public void setResponseComplaints(List<ResponseComplaint> responseComplaints) {
        this.responseComplaints = responseComplaints;
    }
}
