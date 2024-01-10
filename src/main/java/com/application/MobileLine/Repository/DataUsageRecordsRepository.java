package com.application.MobileLine.Repository;

import com.application.MobileLine.Entities.DataUsageRecords;
import com.application.MobileLine.Entities.MobileLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DataUsageRecordsRepository extends JpaRepository<DataUsageRecords, UUID> {

    List<DataUsageRecords> findByMobileLineAndDataUsageDate(MobileLine mobileLine, LocalDate callDate);

    List<DataUsageRecords> findByMobileLineAndDataUsageDateBetween(MobileLine mobileLine, LocalDate startDate,
            LocalDate endDate);
}