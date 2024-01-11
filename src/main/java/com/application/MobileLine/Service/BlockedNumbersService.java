package com.application.MobileLine.Service;

import com.application.MobileLine.Repository.BlockedNumbersRepository;

import jakarta.transaction.Transactional;

import com.application.MobileLine.Entities.BlockedNumbers;
import com.application.MobileLine.Entities.MobileLine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockedNumbersService {
    private final BlockedNumbersRepository blockedNumbersRepository;

    public BlockedNumbersService(BlockedNumbersRepository bNumRepository) {
        this.blockedNumbersRepository = bNumRepository;
    }

    public List<BlockedNumbers> getBlockedNumbersByMobileLine(MobileLine mobileLine) {
        return blockedNumbersRepository.findBlockedNumbersByMobileLineId(mobileLine);
    }

    public BlockedNumbers getBlockedNumberByBlockedNumberAndMobileLine(Integer blockedNumber, MobileLine mobileLine) {
        Optional<BlockedNumbers> bN = blockedNumbersRepository
                .findByBlockedNumberAndMobileLine(blockedNumber, mobileLine);
        if (bN.isPresent()) {
            return bN.get();
        } else {
            return new BlockedNumbers();
        }
    }

    public boolean saveBlockedNumber(BlockedNumbers blockedNumber) {
        try {
            blockedNumbersRepository.save(blockedNumber);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteBlockedNumber(BlockedNumbers blockedNumber) {
        try {
            blockedNumbersRepository.delete(blockedNumber);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}