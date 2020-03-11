package com.BackEnd;

import java.math.BigDecimal;

/**
 *
 */
public class AvailableItems {

    /**
     *
     */
    private String itemName;
    private String sellerName;
    private String currentWinningBidder;
    private int numberOfDaysLeft;
    private BigDecimal highestBid = new BigDecimal("0.0");

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
    public int getNumberOfDaysLeft() {
        return numberOfDaysLeft;
    }

    /**
     *
     * @param numberOfDaysLeft
     */
    public void setNumberOfDaysLeft(int numberOfDaysLeft) {
        this.numberOfDaysLeft = numberOfDaysLeft;
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
    public AvailableItems(){

    }

    /**
     *
     * @param item
     * @param seller
     * @param currentBidder
     * @param days
     * @param bid
     */
    public AvailableItems(String item, String seller, String currentBidder, int days, BigDecimal bid){
        this.itemName = item;
        this.sellerName = seller;
        this.currentWinningBidder = currentBidder;
        this.numberOfDaysLeft = days;
        this.highestBid = bid;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return itemName + " " + sellerName + " " + currentWinningBidder + " " + numberOfDaysLeft + " " + highestBid;
    }

}
