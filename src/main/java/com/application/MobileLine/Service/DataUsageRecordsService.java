package com.application.MobileLine.Service;

import com.application.MobileLine.Entities.DataUsageRecords;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Repository.DataUsageRecordsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DataUsageRecordsService {
    private final DataUsageRecordsRepository dataUsageRecordsRepository;

    public DataUsageRecordsService(DataUsageRecordsRepository dataUsageRecordsRepository) {
        this.dataUsageRecordsRepository = dataUsageRecordsRepository;
    }

    public List<DataUsageRecords> getDataUsageRecordsByMobileLineToday(MobileLine mobileLine) {
        return dataUsageRecordsRepository.findByMobileLineAndDataUsageDate(mobileLine, LocalDate.now());
    }

    public List<DataUsageRecords> getDataUsageRecordsByMobileLineCurrentMonth(MobileLine mobileLine) {
        LocalDate date = LocalDate.now(); // Fecha actual
        LocalDate startOfMonth = date.withDayOfMonth(1); // Fecha de inicio del mes
        LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth()); // Fecha de fin del mes

        return dataUsageRecordsRepository.findByMobileLineAndDataUsageDateBetween(mobileLine, startOfMonth,
                endOfMonth);
    }

    public int count() {
        return (int) dataUsageRecordsRepository.count();
    }
}