package com.example.checkloanbalanceandlimitservice.repository;

import com.example.checkloanbalanceandlimitservice.model.LoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author samwel.wafula
 * Created on 15/03/2024
 * Time 16:42
 * Project CheckLoanBalanceAndLimitService
 */
@Repository
public interface LoanAccountRepository extends JpaRepository<LoanAccount, Integer> {

    Optional<LoanAccount> findAllByPhoneNumber(String phoneNumber);

    @Query(nativeQuery = true, value = "update tbl_loan_account set loan_balance=:loanBalance where phone_number=:phoneNumber")
    @Transactional
    @Modifying
    void updateLoanBalanceToChangeLoanCeiling(@Param("loanBalance") double newLoanBalance, @Param("phoneNumber") String phoneNumber);
}
