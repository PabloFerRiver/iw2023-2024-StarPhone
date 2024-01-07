package com.application.Contract.Repository;

import com.application.Contract.Entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface ContractRepository extends JpaRepository<Contract, UUID> {

        List<Contract> findByUserId(UUID user_id);

        List<Contract> findByUserIdAndStatus(UUID userId, String status);

        Contract findByUserIdAndFeeId(UUID user_id, UUID fee_id);

        List<Contract> findAll();
}
