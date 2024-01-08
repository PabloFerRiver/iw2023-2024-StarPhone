package com.application.Contract.Service;

import org.springframework.stereotype.Service;
import com.application.Contract.Entities.Contract;
import com.application.Contract.Entities.Status;
import com.application.Contract.Repository.ContractRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository cRepository) {

        this.contractRepository = cRepository;
    }

    public Contract getContractById(UUID id) {
        Optional<Contract> c = contractRepository.findById(id);
        if (c.isPresent()) {
            return c.get();
        } else {
            return new Contract();
        }
    }

    public List<Contract> getContractsByUserId(UUID user_id) {
        return contractRepository.findByUserId(user_id);
    }

    public Contract getContractByUserIdAndFeeIdAndStatus(UUID user_id, UUID fee_id, Status status) {
        return contractRepository.findByUserIdAndFeeIdAndStatus(user_id, fee_id, status);
    }

    public List<Contract> getContractsByUserIdAndFeeId(UUID user_id, UUID fee_id) {
        return contractRepository.findByUserIdAndFeeId(user_id, fee_id);
    }

    public boolean saveContract(Contract contract) {
        try {
            contractRepository.save(contract);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteContractByUserIdAndFeeIdAndStatus(UUID user_id, UUID fee_id, Status status) {
        Contract c = contractRepository.findByUserIdAndFeeIdAndStatus(user_id, fee_id, status);
        try {
            contractRepository.delete(c);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    public int count() {
        return (int) contractRepository.count();
    }

    public Contract getContractByUserIdAndStatus(UUID id, Status enproceso) {
        return null;
    }

}
