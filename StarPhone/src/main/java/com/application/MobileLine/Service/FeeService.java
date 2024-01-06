package com.application.MobileLine.Service;

import com.application.MobileLine.Repository.FeeRepository;

import jakarta.transaction.Transactional;

import com.application.MobileLine.Entities.Fee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeeService {
    private final FeeRepository feeRepository;

    public FeeService(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    public List<Fee> getAll() {
        return feeRepository.findAll();
    }

    public Fee getFeeByTitle(String title) {
        Optional<Fee> fee = feeRepository.findByTitle(title);
        if (!fee.isPresent()) {
            return new Fee();
        } else {
            return fee.get();
        }
    }

    public boolean saveFee(Fee fee) {
        try {
            feeRepository.save(fee);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean deleteFee(Fee fee) {
        try {
            feeRepository.delete(fee);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean deleteFeeByTitle(String title) {
        Fee fee = getFeeByTitle(title);
        try {
            feeRepository.delete(fee);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int count() {
        return (int) feeRepository.count();
    }
}