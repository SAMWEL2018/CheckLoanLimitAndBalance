package com.example.checkloanbalanceandlimitservice.model;

import lombok.*;

/**
 * @author samwel.wafula
 * Created on 15/03/2024
 * Time 16:59
 * Project CheckLoanBalanceAndLimitService
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private String responseCode;
    private String responseDescription;
}
