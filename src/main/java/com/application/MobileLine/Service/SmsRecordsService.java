package com.application.MobileLine.Service;

import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Entities.SmsRecords;
import com.application.MobileLine.Repository.SmsRecordsRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SmsRecordsService {
    private final SmsRecordsRepository smsRecordsRepository;

    public SmsRecordsService(SmsRecordsRepository smsRecordsRepository) {
        this.smsRecordsRepository = smsRecordsRepository;
    }

    public List<SmsRecords> getSmsRecordsByMobileLineToday(MobileLine mobileLine) {
        return smsRecordsRepository.findByMobileLineAndSmsDate(mobileLine, LocalDate.now());
    }

    public List<SmsRecords> getSmsRecordsByMobileLineCurrentMonth(MobileLine mobileLine) {
        LocalDate date = LocalDate.now(); // Fecha actual
        LocalDate startOfMonth = date.withDayOfMonth(1); // Fecha de inicio del mes
        LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth()); // Fecha de fin del mes

        return smsRecordsRepository.findByMobileLineAndSmsDateBetween(mobileLine, startOfMonth,
                endOfMonth);
    }

    public int count() {
        return (int) smsRecordsRepository.count();
    }
}
