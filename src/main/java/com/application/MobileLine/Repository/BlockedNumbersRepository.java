package com.application.MobileLine.Repository;

import com.application.MobileLine.Entities.MobileLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlockedNumbersRepository extends JpaRepository<MobileLine, UUID> {

    List<MobileLine> findMobileLineByBlockedNumbers_BlockedNumber(Integer phoneNumber);
}