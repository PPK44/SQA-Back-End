package com.BackEnd;

import java.math.BigDecimal;

/**
 * Stores data on an item as well as its auction information.
 * Item data is acquired from the Files and FileIO classes.
 */
public class AvailableItems {

    /**
     * Data about the item in question, its seller, and about the item's active auction.
     * Auction data includes the highest bidder, the highest bid, and the amount of days left.
     */
    private String itemName;
    private String sellerName;
    private String currentWinningBidder;
    private int numberOfDaysLeft;
    private BigDecimal highestBid = new BigDecimal("0.0");

    /**
     * Gets the item's name.
     * @return the name of the item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the item's name.
     * @param itemName the new name of the item
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the name of the user selling the item.
     * @return the name of the seller
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * Sets the item seller's name.
     * @param sellerName the new name of the seller
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * Gets the current highest bidder in the item's auction.
     * @return the name of the item's highest bidder
     */
    public String getCurrentWinningBidder() {
        return currentWinningBidder;
    }

    /**
     * Sets the highest bidder in the item's auction.
     * @param currentWinningBidder the name of the item's new highest bidder
     */
    public void setCurrentWinningBidder(String currentWinningBidder) {
        this.currentWinningBidder = currentWinningBidder;
    }

    /**
     * Gets the number of days left in the item's auction.
     * @return the number of days left in the item's auction
     */
    public int getNumberOfDaysLeft() {
        return numberOfDaysLeft;
    }

    /**
     * Sets the number of days left in the item's auction.
     * @param numberOfDaysLeft the new number of days left in the item's auction
     */
    public void setNumberOfDaysLeft(int numberOfDaysLeft) {
        this.numberOfDaysLeft = numberOfDaysLeft;
    }

    /**
     * Gets the item's current highest bid price in the auction.
     * @return the item's current highest bid price
     */
    public BigDecimal getHighestBid() {
        return highestBid;
    }

    /**
     * Sets the item's new highest bid price in the auction.
     * @param highestBid the item's new highest bid price
     */
    public void setHighestBid(BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    /**
     * Constructor to create a blank new AvailableItems item with no data.
     */
    public AvailableItems(){

    }

    /**
     * Constructor to create an AvailableItems item with all data provided on both the item and its auction.
     * @param item the item name
     * @param seller the item seller's name
     * @param currentBidder the current highest bidder's name
     * @param days how many days are left in the item's auction
     * @param bid the current highest bid price
     */
    public AvailableItems(String item, String seller, String currentBidder, int days, BigDecimal bid){
        this.itemName = item;
        this.sellerName = seller;
        this.currentWinningBidder = currentBidder;
        this.numberOfDaysLeft = days;
        this.highestBid = bid;
    }

    /**
     * Converts the item to a string.
     * @return a string representation of the item
     */
    @Override
    public String toString(){
        return itemName + " " + sellerName + " " + currentWinningBidder + " " + numberOfDaysLeft + " " + highestBid;
    }

}
