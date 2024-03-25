package com.example.checkloanbalanceandlimitservice;

import com.example.checkloanbalanceandlimitservice.model.ClientResponse;
import com.example.checkloanbalanceandlimitservice.model.LoanAccount;
import com.example.checkloanbalanceandlimitservice.service.LoanServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
@Slf4j
class CheckLoanBalanceAndLimitServiceApplicationTests {

    private final LoanServiceImpl loanService;

    @Autowired
    public CheckLoanBalanceAndLimitServiceApplicationTests(LoanServiceImpl loanService) {
        this.loanService = loanService;
    }

    @Test
    void contextLoads() {
    }


    @Test
    void getLoanBalanceAndLimit() throws JsonProcessingException {
        Optional<LoanAccount> loanAccount = loanService.getLoanBalanceAndLimitAccountDetail("0712321806");

        if (loanAccount.isPresent()) {
            log.info("account details {}",new ObjectMapper().writeValueAsString(loanAccount.get()));
        } else {
            log.info("account not found");
        }

    }


}
