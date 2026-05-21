package com.shunya.accounts.services;

import com.shunya.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto - CustomerDto Object
     * */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Account details based on a given mobile number
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return a boolean indicating if the account update is successful or not
     * */
    boolean updateAccount(CustomerDto customerDto);

    /**
    * @param mobileNumber - customer mobile number
    * @return boolean indicating if the delete of account is successful or not*
    * */
    boolean deleteAccount(String mobileNumber);
}
