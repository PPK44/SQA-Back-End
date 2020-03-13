package com.BackEnd;

import java.math.BigDecimal;

/**
 * This class stores data for a single user account of the auction system.
 */
public class UserAccounts {

    // User data stored: username, user type, available credit, and password
    private String userName;

    /*
     * User type follows this pattern:
     * AA - admin
     * FS - full-standard
     * BS - buy-standard
     * SS - sell-standard
     */
    private String userType;
    private BigDecimal availableCredit = new BigDecimal("0.0");
    private String password;

    /**
     * Gets the stored user's password.
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the stored user's new password.
     * @param password the user's new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the stored user's permissions type.
     * User type follows this pattern:
     * AA - admin
     * FS - full-standard
     * BS - buy-standard
     * SS - sell-standard
     * @return the stored user's type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the stored user's permissions type.
     * User type follows this pattern:
     * AA - admin
     * FS - full-standard
     * BS - buy-standard
     * SS - sell-standard
     * @param userType the stored user's new type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Gets the stored user's username.
     * @return the stored user's username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the stored user's username.
     * @param userName the stored user's new username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the amount of available credit the user has.
     * @return the user's available credit
     */
    public BigDecimal getAvailableCredit() {
        return availableCredit;
    }

    /**
     * Sets the amount of available credit the user has.
     * @param availableCredit the user's new available credit amount
     */
    public void setAvailableCredit(BigDecimal availableCredit) {
        this.availableCredit = availableCredit;
    }

    /**
     * Creates a blank UserAccounts instance with no data.
     */
    public UserAccounts(){

    }

    /**
     * Creates a UserAccounts instance with all data specified.
     * This stores a new user account for the auctions program.
     * @param user the name of the user
     * @param type the two-character user permissions type
     * @param pwd the user's password
     * @param credit the amount of credit the user has
     */
    public UserAccounts(String user, String type, String pwd,BigDecimal credit){
        this.userName = user;
        this.userType = type;
        this.availableCredit = credit;
        this.password = pwd;
    }

    /**
     * Converts the user data to a string representation.
     * @return the user in string form
     */
    @Override
    public String toString()
    {
        return userName + " " + password + " " + userType + " " + availableCredit;
    }

}
