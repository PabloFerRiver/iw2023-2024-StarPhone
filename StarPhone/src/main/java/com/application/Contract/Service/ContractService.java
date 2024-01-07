package com.application.Contract.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.application.Contract.Entities.Contract;
import com.application.Contract.Repository.ContractRepository;

import jakarta.transaction.Transactional;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository cRepository) {

        this.contractRepository = cRepository;
    }

    public List<Contract> getContractsByUserId(UUID user_id) {
        return contractRepository.findByUserId(user_id);
    }

    public List<Contract> getContractByUserIdAndStatus(UUID user_id, String status) {
        return contractRepository.findByUserIdAndStatus(user_id, status);
    }

    public Contract getContractByUserIdAndFeeId(UUID user_id, UUID fee_id) {
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
    public boolean deleteContractByUserIdAndFeeId(UUID user_id, UUID fee_id) {
        Contract c = contractRepository.findByUserIdAndFeeId(user_id, fee_id);
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

}
