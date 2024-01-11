package com.application.MobileLine.Repository;

import com.application.MobileLine.Entities.BlockedNumbers;
import com.application.MobileLine.Entities.MobileLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlockedNumbersRepository extends JpaRepository<BlockedNumbers, UUID> {

    List<BlockedNumbers> findBlockedNumbersByMobileLineId(MobileLine mobileLine);

    Optional<BlockedNumbers> findByBlockedNumberAndMobileLine(Integer blockedNumber,
            MobileLine mobileLine);
}