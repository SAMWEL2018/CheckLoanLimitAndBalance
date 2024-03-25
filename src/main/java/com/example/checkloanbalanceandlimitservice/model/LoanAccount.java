package com.example.checkloanbalanceandlimitservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * @author samwel.wafula
 * Created on 15/03/2024
 * Time 16:34
 * Project CheckLoanBalanceAndLimitService
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_loan_account")
public class LoanAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phoneNumber;
    private double loanBalance;
    private double loanLimit;

}
