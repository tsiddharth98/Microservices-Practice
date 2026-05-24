package com.shunya.loans.service;

import com.shunya.loans.dto.LoansDto;
import com.shunya.loans.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface ILoansService {

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of loan details is successful or not
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(String mobileNumber);
}
