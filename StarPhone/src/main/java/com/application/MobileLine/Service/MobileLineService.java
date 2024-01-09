package com.application.MobileLine.Service;

import com.application.MobileLine.Entities.BlockedNumbers;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Repository.MobileLineRepository;

import jakarta.transaction.Transactional;

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

    public boolean saveMobileLine(MobileLine mobileLine) {
        try {
            mobileLineRepository.save(mobileLine);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean blockNumber(int phoneNumberToBlock, int phoneNumber) {
        BlockedNumbers blockedNumber = new BlockedNumbers();
        MobileLine mLine = mobileLineRepository.findByPhoneNumber(phoneNumber);
        blockedNumber.setBlockedNumber(phoneNumberToBlock);
        mLine.addBlockedNumber(blockedNumber);
        try {
            mobileLineRepository.save(mLine);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean unblockNumber(int phoneNumberToUnblock, int phoneNumber) {
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
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<MobileLine> getMobileLinesByContractId(UUID contract_id) {
        return mobileLineRepository.findMobileLineByContract_Id(contract_id);
    }

    public List<MobileLine> getMobileLinesByUserId(UUID user_id) {
        return mobileLineRepository.findMobileLineByUser_Id(user_id);
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

    @Transactional
    public boolean deleteMobileLinesByContractId(UUID contract_id) {
        List<MobileLine> mobileLines = mobileLineRepository.findMobileLineByContract_Id(contract_id);
        try {
            for (MobileLine m : mobileLines) {
                mobileLineRepository.delete(m);
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteMobileLineById(UUID id) {
        try {
            mobileLineRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
