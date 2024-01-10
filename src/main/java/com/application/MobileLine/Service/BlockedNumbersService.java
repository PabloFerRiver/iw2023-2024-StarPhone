package com.application.MobileLine.Service;

import com.application.MobileLine.Repository.BlockedNumbersRepository;
import com.application.MobileLine.Entities.MobileLine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockedNumbersService {
    private final BlockedNumbersRepository blockedNumbersRepository;

    public BlockedNumbersService(BlockedNumbersRepository bNumRepository) {
        this.blockedNumbersRepository = bNumRepository;
    }

    public boolean isBlockedNumberByPhoneNumber(Integer phoneNumberBlocked, Integer phoneNumber) {
        List<MobileLine> mobileLines = blockedNumbersRepository
                .findMobileLineByBlockedNumbers_BlockedNumber(phoneNumberBlocked);
        for (MobileLine mobileLine : mobileLines) {
            if (mobileLine.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}