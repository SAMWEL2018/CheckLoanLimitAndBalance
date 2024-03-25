package com.example.checkloanbalanceandlimitservice.service;

import com.example.checkloanbalanceandlimitservice.model.ClientResponse;
import com.example.checkloanbalanceandlimitservice.model.LoanAccount;
import com.example.checkloanbalanceandlimitservice.model.LoanObjectForRequestAndRepay;

import java.util.Optional;

/**
 * @author samwel.wafula
 * Created on 15/03/2024
 * Time 16:37
 * Project CheckLoanBalanceAndLimitService
 */
public interface LoanService {
    Optional<LoanAccount> getLoanBalanceAndLimitAccountDetail(String phoneNumber);

    ClientResponse initializeLoanLimitAccount(LoanAccount loanAccount);

    ClientResponse requestLoan(LoanObjectForRequestAndRepay loanObjectForRequestAndRepay);
    ClientResponse repayLoan(LoanObjectForRequestAndRepay loanObjectForRequestAndRepay);
}
