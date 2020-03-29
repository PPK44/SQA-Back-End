package com.BackEnd;

import java.math.BigDecimal;

/**
 * Stores data about a transaction a user performed on the frontend.
 * Different transactions on the frontend are logged when the user inputs different commands.
 * An example transaction would involve bidding on an item.
 */
public class Transactions {

    /**
     * Data about a transaction a user performed, as well as the user's info.
     * Some variables only pertain to one (or a few) transaction types, instead of all of them.
     */
    private int transactionCode;
    private String userName;
    private String userType;
    private BigDecimal availableCredit = new BigDecimal("0.00");
    private String buyerName;
    private String sellerName;
    private BigDecimal refundCredit = new BigDecimal("0.00");
    private int daysToAuction;
    private BigDecimal minBid = new BigDecimal("0.00");
    private String itemName;
    private BigDecimal newBid = new BigDecimal("0.00");
    private String currentWinningBidder;
    private BigDecimal highestBid = new BigDecimal("0.00");

    /**
     * Gets the transaction code from this Transactions instance.
     * Each transaction code definition is listed below:
     * 00 - end of session
     * 01 - create
     * 02 - delete
     * 03 - advertise
     * 04 - bid
     * 05 - refund
     * 06 - end of session
     * @return the two-digit transaction code for this instance
     */
    public int getTransactionCode() {
        return transactionCode;
    }

    /**
     * Sets the transaction code for this Transactions instance.
     * Each transaction code definition is listed below:
     * 00 - end of session
     * 01 - create
     * 02 - delete
     * 03 - advertise
     * 04 - bid
     * 05 - refund
     * 06 - end of session
     * @param id the new two-digit transaction code for this instance
     */
    public void setTransactionCode(int id) {
        this.transactionCode = id;
    }

    /**
     * Gets the name of the user who started the transaction.
     * @return the name of the user who initiated the transaction
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the name of the user who started the transaction.
     * @param userName the new name of the user who initiated the transaction
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the type of the user who started the transaction.
     * The type can be as follows:
     * AA - admin
     * FS - full-standard
     * BS - buy-standard
     * SS - sell-standard
     * @return the creator's two-character user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the stored type of the user who started the transaction.
     * The type can be as follows:
     * AA - admin
     * FS - full-standard
     * BS - buy-standard
     * SS - sell-standard
     * @param userType the creator's new two-character user type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Gets the amount of available credit the transaction creator has.
     * @return the creator's available credit amount
     */
    public BigDecimal getAvailableCredit() {
        return availableCredit;
    }

    /**
     * Sets the amount of available credit the transaction creator has.
     * @param availableCredit the creator's new available credit amount
     */
    public void setAvailableCredit(BigDecimal availableCredit) {
        this.availableCredit = availableCredit;
    }

    /**
     * Gets the buyer's username in this transaction.
     * @return the buyer's username
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * Sets the buyer's username in this transaction.
     * @param buyerName the buyer's new username
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * Gets the seller's username in this transaction.
     * @return the seller's username
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * Sets the seller's username in this transaction.
     * @param sellerName the seller's new username
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * Gets the amount of credit to refund in this transaction.
     * @return the amount of credit that this transaction is refunding
     */
    public BigDecimal getRefundCredit() {
        return refundCredit;
    }

    /**
     * Sets the amount of credit to refund in this transaction.
     * @param refundCredit the new amount of credit that this transaction is refunding
     */
    public void setRefundCredit(BigDecimal refundCredit) {
        this.refundCredit = refundCredit;
    }

    /**
     * Gets the amount of days left until the end of the auction.
     * @return the amount of remaining auction days
     */
    public int getDaysToAuction() {
        return daysToAuction;
    }

    /**
     * Sets the amount of days left until the end of the auction.
     * @param daysToAuction the amount of remaining auction days
     */
    public void setDaysToAuction(int daysToAuction) {
        this.daysToAuction = daysToAuction;
    }

    /**
     * Gets the minimum bid amount for this transaction.
     * @return the minimum bid price
     */
    public BigDecimal getMinBid() {
        return minBid;
    }

    /**
     * Sets the minimum bid amount for this transaction.
     * @param minBid the minimum bid price
     */
    public void setMinBid(BigDecimal minBid) {
        this.minBid = minBid;
    }

    /**
     * Gets the name of the item specified in the transaction.
     * @return the transaction item's name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the new name of the item specified in the transaction.
     * @param itemName the new transaction item's name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the new bid price specified in the transaction.
     * @return the bid amount
     */
    public BigDecimal getNewBid() {
        return newBid;
    }

    /**
     * Sets the new bid price specified in this transaction.
     * @param newBid the new bid amount
     */
    public void setNewBid(BigDecimal newBid) {
        this.newBid = newBid;
    }

    /**
     * Gets the name of the current winning bidder.
     * @return the current winning bidder's name
     */
    public String getCurrentWinningBidder() {
        return currentWinningBidder;
    }

    /**
     * Sets the name of the current winning bidder.
     * @param currentWinningBidder the new current winning bidder's name
     */
    public void setCurrentWinningBidder(String currentWinningBidder) {
        this.currentWinningBidder = currentWinningBidder;
    }

    /**
     * Gets the current highest bid amount.
     * @return the highest bid price
     */
    public BigDecimal getHighestBid() {
        return highestBid;
    }

    /**
     * Sets the highest bid amount.
     * @param highestBid the new highest bid price
     */
    public void setHighestBid(BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    /**
     * Creates a blank Transactions instance, with no data.
     */
    public Transactions(){

    }

    /**
     * Creates a new Transactions instance. This instance stores data on a logged Transaction, created from a command being run on the frontend.
     * @param transactionCode the two-digit transaction code indicating what command was used
     * @param userName the transaction caller's username
     * @param userType the two-character string indicating the transaction caller's user type
     * @param availableCredit the amount of available credit
     * @param buyerName the buyer's name
     * @param sellerName the seller's name
     * @param refundCredit the amount of credit to refund
     * @param daysToAuction days left in the auction
     * @param minBid the minimum bid price
     * @param itemName the name of the item
     * @param newBid the new bid price
     * @param currentWinningBidder the current winning bidder's name
     * @param highestBid the highest bid price
     */
    public Transactions(int transactionCode, String userName, String userType, BigDecimal availableCredit, String buyerName, String sellerName, BigDecimal refundCredit, int daysToAuction, BigDecimal minBid, String itemName, BigDecimal newBid, String currentWinningBidder, BigDecimal highestBid) {
        this.transactionCode = transactionCode;
        this.userName = userName;
        this.userType = userType;
        this.availableCredit = availableCredit;
        this.buyerName = buyerName;
        this.sellerName = sellerName;
        this.refundCredit = refundCredit;
        this.daysToAuction = daysToAuction;
        this.minBid = minBid;
        this.itemName = itemName;
        this.newBid = newBid;
        this.currentWinningBidder = currentWinningBidder;
        this.highestBid = highestBid;
    }

}
