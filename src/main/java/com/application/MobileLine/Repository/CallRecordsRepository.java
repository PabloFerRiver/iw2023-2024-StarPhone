package com.application.MobileLine.Repository;

import com.application.MobileLine.Entities.CallRecords;
import com.application.MobileLine.Entities.MobileLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CallRecordsRepository extends JpaRepository<CallRecords, UUID> {

    List<CallRecords> findByMobileLineAndCallDate(MobileLine mobileLine, LocalDate callDate);

    List<CallRecords> findByMobileLineAndCallDateBetween(MobileLine mobileLine, LocalDate startDate,
            LocalDate endDate);
}