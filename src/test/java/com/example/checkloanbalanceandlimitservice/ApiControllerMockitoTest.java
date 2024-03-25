package com.example.checkloanbalanceandlimitservice;


import com.example.checkloanbalanceandlimitservice.controller.ApiController;
import com.example.checkloanbalanceandlimitservice.model.LoanAccount;
import com.example.checkloanbalanceandlimitservice.model.LoanObjectForRequestAndRepay;
import com.example.checkloanbalanceandlimitservice.repository.DataLayer;
import com.example.checkloanbalanceandlimitservice.repository.LoanAccountRepository;
import com.example.checkloanbalanceandlimitservice.service.LoanServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author samwel.wafula
 * Created on 19/03/2024
 * Time 13:49
 * Project RegisterService
 */

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ApiController.class)
public class ApiControllerMockitoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanServiceImpl loanService;
    @MockBean
    private LoanAccountRepository loanAccountRepository;
    @MockBean
    private DataLayer dataLayer;

    @Test
    void initializeAccount() throws Exception {
        LoanAccount loanAccount = LoanAccount.builder().loanLimit(20000).loanBalance(0).phoneNumber("0714689411").build();
        ResultActions response = mockMvc.perform(post("/initializeLoanLimitAccount").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(loanAccount)));
        response.andExpect(status().isOk());

    }

    @Test
    void loanRequest() throws Exception {

        LoanObjectForRequestAndRepay loanRequest = LoanObjectForRequestAndRepay.builder().phoneNumber("0712321806").amount(1000).build();
        ResultActions response = mockMvc.perform(post("/requestLoan").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(loanRequest)));
        response.andExpect(status().isOk());

    }

    @Test
    void loanRepay() throws Exception {

        LoanObjectForRequestAndRepay loanRepay = LoanObjectForRequestAndRepay.builder().phoneNumber("0712321806").amount(1000).build();
        ResultActions response = mockMvc.perform(post("/repayLoan").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(loanRepay)));
        response.andExpect(status().isOk());

    }
    @Test
    void getLoanBalanceAndLimit() throws Exception {

        ResultActions response = mockMvc.perform(get("/getLoanAccountDetails/0712321806"));
        response.andExpect(status().isOk());

    }
}
