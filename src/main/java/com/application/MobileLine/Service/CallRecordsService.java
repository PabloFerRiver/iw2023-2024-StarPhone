package com.application.MobileLine.Service;

import com.application.MobileLine.Entities.CallRecords;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Repository.CallRecordsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CallRecordsService {
    private final CallRecordsRepository callRecordsRepository;

    public CallRecordsService(CallRecordsRepository callRecordsRepository) {
        this.callRecordsRepository = callRecordsRepository;
    }

    public List<CallRecords> getCallRecordsByMobileLineToday(MobileLine mobileLine) {
        return callRecordsRepository.findByMobileLineAndCallDate(mobileLine, LocalDate.now());
    }

    public List<CallRecords> getCallRecordsByMobileLineCurrentMonth(MobileLine mobileLine) {
        LocalDate date = LocalDate.now(); // Fecha actual
        LocalDate startOfMonth = date.withDayOfMonth(1); // Fecha de inicio del mes
        LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth()); // Fecha de fin del mes

        return callRecordsRepository.findByMobileLineAndCallDateBetween(mobileLine, startOfMonth,
                endOfMonth);
    }

    public int count() {
        return (int) callRecordsRepository.count();
    }
}