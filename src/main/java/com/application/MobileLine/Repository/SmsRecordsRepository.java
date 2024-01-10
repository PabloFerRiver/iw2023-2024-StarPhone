package com.application.MobileLine.Repository;

import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Entities.SmsRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface SmsRecordsRepository extends JpaRepository<SmsRecords, UUID> {

    List<SmsRecords> findByMobileLineAndSmsDate(MobileLine mobileLine, LocalDate callDate);

    List<SmsRecords> findByMobileLineAndSmsDateBetween(MobileLine mobileLine, LocalDate startDate,
            LocalDate endDate);
}