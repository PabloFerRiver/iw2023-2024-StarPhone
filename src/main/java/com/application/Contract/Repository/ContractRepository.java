package com.application.Contract.Repository;

import com.application.Contract.Entities.Contract;
import com.application.Contract.Entities.StatusContract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface ContractRepository extends JpaRepository<Contract, UUID> {

        Optional<Contract> findById(UUID id);

        List<Contract> findByUserId(UUID user_id);

        List<Contract> findByFeeId(UUID fee_id);

        List<Contract> findByUserIdAndFeeId(UUID user_id, UUID fee_id);

        Contract findByUserIdAndFeeIdAndStatus(UUID userId, UUID fee_id, StatusContract status);

        List<Contract> findByUserIdAndStatus(UUID user_id, StatusContract status);

        List<Contract> findAll();
}
