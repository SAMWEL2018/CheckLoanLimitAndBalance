package com.example.checkloanbalanceandlimitservice.controller;

import com.example.checkloanbalanceandlimitservice.model.ClientResponse;
import com.example.checkloanbalanceandlimitservice.model.LoanAccount;
import com.example.checkloanbalanceandlimitservice.model.LoanObjectForRequestAndRepay;
import com.example.checkloanbalanceandlimitservice.service.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * @author samwel.wafula
 * Created on 15/03/2024
 * Time 16:48
 * Project CheckLoanBalanceAndLimitService
 */
@Controller
public class ApiController {

    private final LoanServiceImpl loanService;

    @Autowired
    public ApiController(LoanServiceImpl loanService) {
        this.loanService = loanService;
    }

    /**
     * ------------------------------------------------------------------------------------------
     * ---------***  ENDPOINT TO GET ACCOUNT DETAILS***-----------------
     * ------------------------------------------------------------------------------------------
     */

    @RequestMapping(value = "/getLoanAccountDetails/{phoneNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> loanAccountDetails(@PathVariable("phoneNumber") String phoneNumber) {
        Optional<LoanAccount> loanAccount = loanService.getLoanBalanceAndLimitAccountDetail(phoneNumber);

        if (loanAccount.isPresent()) {
            return ResponseEntity.status(200).body(loanAccount);
        } else {
            return ResponseEntity.status(200).body(ClientResponse.builder().responseCode("200")
                    .responseDescription("Loan Account Not Found").build());
        }
    }

    /**
     * ------------------------------------------------------------------------------------------
     * ---------***  ENDPOINT CONSUMED BY REGISTER SERVICE TO INITIALIZE LOAN ACCOUNT***-----------------
     * ------------------------------------------------------------------------------------------
     */

    @RequestMapping(value = "/initializeLoanLimitAccount", method = RequestMethod.POST)
    public ResponseEntity<?> initializeLoanLimitAccount(@RequestBody LoanAccount loanAccount) {
        if (loanAccount != null) {
            ClientResponse response = loanService.initializeLoanLimitAccount(loanAccount);
            return ResponseEntity.status(200).body(response);
        } else {
            return ResponseEntity.status(400).body(ClientResponse.builder().responseCode("400").responseDescription("Empty Loan Object").build());
        }
    }

    /**
     * ------------------------------------------------------------------------------------------
     * ------------*** ENDPOINT CONSUMED BY REQUEST LOAN SERVICE TO REQUEST LOAN ***-----------------
     * ------------------------------------------------------------------------------------------
     */

    @RequestMapping(value = "/requestLoan", method = RequestMethod.POST)
    public ResponseEntity<?> requestLoan(@RequestBody LoanObjectForRequestAndRepay loanObjectForRequestAndRepay) {
        if (loanObjectForRequestAndRepay != null) {
            ClientResponse clientResponse = loanService.requestLoan(loanObjectForRequestAndRepay);
            return ResponseEntity.status(200).body(clientResponse);
        } else {
            return ResponseEntity.status(400).body(ClientResponse.builder().responseCode("400").responseDescription("Error on request Object").build());
        }
    }

    /**
     * ------------------------------------------------------------------------------------------
     * ------------*** ENDPOINT CONSUMED BY REPAY LOAN SERVICE TO REPAY LOAN ***-----------------
     * ------------------------------------------------------------------------------------------
     */

    @RequestMapping(value = "/repayLoan", method = RequestMethod.POST)
    public ResponseEntity<?> repayLoan(@RequestBody LoanObjectForRequestAndRepay repayLoan) {

        if (repayLoan != null) {
            ClientResponse res = loanService.repayLoan(repayLoan);
            return ResponseEntity.status(200).body(res);
        } else {
            return ResponseEntity.status(400).body(ClientResponse.builder().responseCode("400").responseDescription("Empty Request Object").build());

        }
    }
}
