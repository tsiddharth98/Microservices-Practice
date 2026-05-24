package com.shunya.loans.service.impl;

import com.shunya.loans.constants.LoansConstant;
import com.shunya.loans.dto.LoansDto;
import com.shunya.loans.dto.ResponseDto;
import com.shunya.loans.entity.Loans;
import com.shunya.loans.exception.LoanAlreadyExistsException;
import com.shunya.loans.exception.ResourceNotFoundException;
import com.shunya.loans.mapper.LoansMapper;
import com.shunya.loans.repository.LoansRepository;
import com.shunya.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        loansRepository.findByMobileNumber(mobileNumber)
                .ifPresent((loan) -> {
                    throw new LoanAlreadyExistsException("Loan already exist for mobile number: "+mobileNumber);
                });

        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans loan = new Loans();
        loan.setMobileNumber(mobileNumber);
        long randomLoanNumber = 1000000000L + new Random().nextInt(900000000);
        loan.setLoanNumber(String.valueOf(randomLoanNumber));
        loan.setLoanType(LoansConstant.HOME_LOAN);
        loan.setTotalLoan(LoansConstant.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoansConstant.NEW_LOAN_LIMIT);
        return loan;
    }

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        return LoansMapper.mapToLoansDto(loan, new LoansDto());
    }

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loan = loansRepository.findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", loansDto.getMobileNumber()));
        LoansMapper.mapToLoans(loansDto, loan);
        loansRepository.save(loan);
        return true;
    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        loansRepository.deleteById(loan.getLoanId());
        return true;
    }
}
