package com.example.checkloanbalanceandlimitservice.service;

import com.example.checkloanbalanceandlimitservice.model.ClientResponse;
import com.example.checkloanbalanceandlimitservice.model.LoanAccount;
import com.example.checkloanbalanceandlimitservice.model.LoanObjectForRequestAndRepay;
import com.example.checkloanbalanceandlimitservice.repository.DataLayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author samwel.wafula
 * Created on 15/03/2024
 * Time 16:38
 * Project CheckLoanBalanceAndLimitService
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoanServiceImpl implements LoanService {
    private final DataLayer dataLayer;

    @Override
    public Optional<LoanAccount> getLoanBalanceAndLimitAccountDetail(String phoneNumber) {

        return dataLayer.findLoanAccountBalanceAndLimit(phoneNumber);
    }

    @Override
    public ClientResponse initializeLoanLimitAccount(LoanAccount loanAccount) {
        dataLayer.initializeLoanLimitAccount(loanAccount);
        return ClientResponse.builder()
                .responseCode("200")
                .responseDescription("Account Initialized for Loan limit ")
                .build();
    }

    @Override
    public ClientResponse requestLoan(LoanObjectForRequestAndRepay loanObjectForRequestAndRepay) {
        Optional<LoanAccount> account = getLoanBalanceAndLimitAccountDetail(loanObjectForRequestAndRepay.getPhoneNumber());
        if (account.isPresent()) {
            double amountCapableToBeLoaned = account.get().getLoanLimit() - account.get().getLoanBalance();
            if (loanObjectForRequestAndRepay.getAmount() < amountCapableToBeLoaned) {
                double newLoanBalance = account.get().getLoanBalance() + loanObjectForRequestAndRepay.getAmount();
                log.info("prev loan bal {}", account.get().getLoanBalance());
                log.info("new loan bal {}", newLoanBalance);
                dataLayer.updateLoanBalanceToChangeLoanCeiling(newLoanBalance, loanObjectForRequestAndRepay.getPhoneNumber());
                return ClientResponse.builder().responseCode("200").responseDescription("Loan has been successfully processed").build();
            } else {
                return ClientResponse.builder().responseCode("200").responseDescription("Loan limit exceeded").build();
            }
        } else {
            return ClientResponse.builder().responseCode("200").responseDescription("Loan account does not exist").build();
        }
    }

    @Override
    public ClientResponse repayLoan(LoanObjectForRequestAndRepay loanObjectForRequestAndRepay) {
        Optional<LoanAccount> account = getLoanBalanceAndLimitAccountDetail(loanObjectForRequestAndRepay.getPhoneNumber());
        if (account.isPresent()) {
            double newLoanBalance = account.get().getLoanBalance() - loanObjectForRequestAndRepay.getAmount();
            dataLayer.updateLoanBalanceToChangeLoanCeiling(newLoanBalance, loanObjectForRequestAndRepay.getPhoneNumber());
            return ClientResponse.builder().responseCode("200").responseDescription("Loan payment has been made, you have a loan balance of " + newLoanBalance).build();
        } else {
            return ClientResponse.builder().responseCode("200").responseDescription("Account to Repay Loan is not present").build();
        }
    }


}
