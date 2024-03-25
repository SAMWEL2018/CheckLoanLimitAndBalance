package com.example.checkloanbalanceandlimitservice.repository;

import com.example.checkloanbalanceandlimitservice.model.ClientResponse;
import com.example.checkloanbalanceandlimitservice.model.LoanAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samwel.wafula
 * Created on 15/03/2024
 * Time 16:43
 * Project CheckLoanBalanceAndLimitService
 */
@Repository
public class DataLayer {

    private final LoanAccountRepository loanAccountRepository;

    @Autowired
    public DataLayer(LoanAccountRepository loanAccountRepository) {
        this.loanAccountRepository = loanAccountRepository;
    }

    public Optional<LoanAccount> findLoanAccountBalanceAndLimit(String phoneNumber) {
        return loanAccountRepository.findAllByPhoneNumber(phoneNumber);
    }

    public void initializeLoanLimitAccount(LoanAccount loanAccount) {
        loanAccountRepository.save(loanAccount);
    }

    public void updateLoanBalanceToChangeLoanCeiling(double newLoanBalance, String phoneNumber) {
        loanAccountRepository.updateLoanBalanceToChangeLoanCeiling(newLoanBalance, phoneNumber);
    }
}
