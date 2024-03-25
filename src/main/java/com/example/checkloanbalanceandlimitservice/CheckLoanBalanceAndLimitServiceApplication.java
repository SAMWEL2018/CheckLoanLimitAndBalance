package com.example.checkloanbalanceandlimitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CheckLoanBalanceAndLimitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckLoanBalanceAndLimitServiceApplication.class, args);
    }

}
