package com.BackEnd;

import java.math.BigDecimal;

/**
 *
 */
public class UserAccounts {

    /**
     *
     */
    String userName;
    String userType;
    BigDecimal availableCredit = new BigDecimal("0.0");
    String password;

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     *
     * @return
     */
    public String getUserType() {
        return userType;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param availableCredit
     */
    public void setAvailableCredit(BigDecimal availableCredit) {
        this.availableCredit = availableCredit;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAvailableCredit() {
        return availableCredit;
    }

    UserAccounts(){

    }

    UserAccounts(String user, String type, String pwd,BigDecimal credit){
        this.userName = user;
        this.userType = type;
        this.availableCredit = credit;
        this.password = pwd;
    }


    @Override
    public String toString()
    {
        return userName + " " + password + " " + userType + " " + availableCredit;
    }



}
