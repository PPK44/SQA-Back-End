package com.BackEnd;

import java.math.BigDecimal;

/**
 *
 */
public class Transactions {

    /**
     *
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
     *
     * @return
     */
    public int getTransactionCode() {
        return transactionCode;
    }

    /**
     *
     * @param id
     */
    public void setTransactionCode(int id) {
        this.transactionCode = id;
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
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAvailableCredit() {
        return availableCredit;
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
    public String getBuyerName() {
        return buyerName;
    }

    /**
     *
     * @param buyerName
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     *
     * @return
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     *
     * @param sellerName
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     *
     * @return
     */
    public BigDecimal getRefundCredit() {
        return refundCredit;
    }

    /**
     *
     * @param refundCredit
     */
    public void setRefundCredit(BigDecimal refundCredit) {
        this.refundCredit = refundCredit;
    }

    /**
     *
     * @return
     */
    public int getDaysToAuction() {
        return daysToAuction;
    }

    /**
     *
     * @param daysToAuction
     */
    public void setDaysToAuction(int daysToAuction) {
        this.daysToAuction = daysToAuction;
    }

    /**
     *
     * @return
     */
    public BigDecimal getMinBid() {
        return minBid;
    }

    /**
     *
     * @param minBid
     */
    public void setMinBid(BigDecimal minBid) {
        this.minBid = minBid;
    }

    /**
     *
     * @return
     */
    public String getItemName() {
        return itemName;
    }

    /**
     *
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     *
     * @return
     */
    public BigDecimal getNewBid() {
        return newBid;
    }

    /**
     *
     * @param newBid
     */
    public void setNewBid(BigDecimal newBid) {
        this.newBid = newBid;
    }

    /**
     *
     * @return
     */
    public String getCurrentWinningBidder() {
        return currentWinningBidder;
    }

    /**
     *
     * @param currentWinningBidder
     */
    public void setCurrentWinningBidder(String currentWinningBidder) {
        this.currentWinningBidder = currentWinningBidder;
    }

    /**
     *
     * @return
     */
    public BigDecimal getHighestBid() {
        return highestBid;
    }

    /**
     *
     * @param highestBid
     */
    public void setHighestBid(BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    /**
     *
     */
    public Transactions(){

    }

    /**
     *
     * @param transactionCode
     * @param userName
     * @param userType
     * @param availableCredit
     * @param buyerName
     * @param sellerName
     * @param refundCredit
     * @param daysToAuction
     * @param minBid
     * @param itemName
     * @param newBid
     * @param currentWinningBidder
     * @param highestBid
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
