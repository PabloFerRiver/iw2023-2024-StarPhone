package com.application.MobileLine.Service;

import com.application.MobileLine.Entities.BlockedNumbers;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Repository.MobileLineRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class MobileLineService {
    private final MobileLineRepository mobileLineRepository;

    public MobileLineService(MobileLineRepository mRepository) {
        this.mobileLineRepository = mRepository;
    }

    public void blockNumber(int phoneNumberToBlock, int phoneNumber) {
        BlockedNumbers blockedNumber = new BlockedNumbers();
        MobileLine mLine = mobileLineRepository.findByPhoneNumber(phoneNumber);
        blockedNumber.setBlockedNumber(phoneNumberToBlock);
        mLine.addBlockedNumber(blockedNumber);
        try {
            mobileLineRepository.save(mLine);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void unblockNumber(int phoneNumberToUnblock, int phoneNumber) {
        BlockedNumbers unblockedNumber = new BlockedNumbers();
        MobileLine mLine = mobileLineRepository.findByPhoneNumber(phoneNumber);
        unblockedNumber.setBlockedNumber(phoneNumberToUnblock);

        List<BlockedNumbers> bl = new ArrayList<>();
        for (BlockedNumbers u : mLine.getBlockedNumbers()) {
            if (!u.getBlockedNumber().equals(unblockedNumber.getBlockedNumber())) {
                bl.add(u);
            }
        }
        mLine.setBlockedNumbers(bl);
        try {
            mobileLineRepository.save(mLine);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<MobileLine> getMobileLineByContractId(UUID contract_id) {
        return mobileLineRepository.findMobileLineByContract_Id(contract_id);
    }

    public void manageShareData(Integer phoneNumber, boolean shareData) {
        MobileLine mobileLine = mobileLineRepository.findByPhoneNumber(phoneNumber);
        if (mobileLine.getId() != null) {
            mobileLine.setShareData(shareData);
            mobileLineRepository.save(mobileLine);
        }

    }

    public void manageRoaming(Integer phoneNumber, boolean roaming) {
        MobileLine mobileLine = mobileLineRepository.findByPhoneNumber(phoneNumber);
        if (mobileLine.getId() != null) {
            mobileLine.setRoaming(roaming);
            mobileLineRepository.save(mobileLine);
        }
    }

    public MobileLine getMobileLineByPhoneNumber(int phoneNumber) {
        return mobileLineRepository.findByPhoneNumber(phoneNumber);
    }
}
