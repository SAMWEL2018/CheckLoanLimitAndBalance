package com.example.checkloanbalanceandlimitservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

/**
 * @author samwel.wafula
 * Created on 16/03/2024
 * Time 08:52
 * Project RequestLoan
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoanObjectForRequestAndRepay {

    private String phoneNumber;
    @JsonProperty("loanAmount")
    @Column(name = "loan_amount")
    private double amount;
}
