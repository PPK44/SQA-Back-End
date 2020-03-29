package com.BackEnd;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.BackEnd.AvailableItems;
import com.BackEnd.UserAccounts;
import com.BackEnd.FileIO;
import com.BackEnd.Transactions;
import com.sun.source.tree.NewArrayTree;

/**
 * This class handles file storage an updating files. It utilizes the FileIO class for file input.
 * EXPLANATION: All print statements are for the Terminal Log
 */
public class Files {

    // Constants
    final String DISABLE_CODE = "DS";
    final BigDecimal MAX_CREDIT = new BigDecimal("999999.99");

    /**
     * Updates and stores the list of users.
     * @throws FileNotFoundException if the user file is missing
     */
    public void updateUserList(List<UserAccounts> users, List<Transactions> transactions) throws IOException {

        for (Transactions transaction: transactions) {
            switch (transaction.getTransactionCode()) {
                case 1: //Create
                    create(transaction, users);
                    break;
                case 2:  //Delete
                    delete(transaction, users);
                    break;
                case 5: //Refund
                    refund(transaction, users);
                    break;
                case 6:  //Add credit
                    addCredit(transaction, users);
                    break;
                case 7: //Enable
                    enable(transaction, users);
                    break;
                case 8: //Disable
                    disable(transaction, users);
                    break;
            }
        }

    }


    /**
     * Updates and stores the available item list.
     * @param transactions holds the list of transactions to go through
     * @param items holds list of items
     * @throws FileNotFoundException if the items file is missing
     */
    public void updateAvailableItemsList(List<AvailableItems> items, List<Transactions> transactions) throws IOException {

        for (Transactions transaction: transactions) {
            switch (transaction.getTransactionCode()) {
                case 3:  //Advertise
                    advertise(transaction, items);
                    break;
                case 4: //Bid
                    bid(transaction, items);
                    break;
            }
        }
    }

    /**
     * Creates a new user with information from the transaction and adds it into the list
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param users holds the list of users
     */
    public void create(Transactions transaction, List<UserAccounts> users) {
        boolean check = false;

        for (UserAccounts userAccounts : users) {
            if (userAccounts.getUserName().equals(transaction.getUserName())) {
                check = true;
                System.out.println("Attempted to create User " + transaction.getUserName().trim() + " but username already exits");
                break;
            }
        }

        if(!check){
            UserAccounts user = new UserAccounts();
            user.setUserType(transaction.getUserType());
            user.setAvailableCredit(transaction.getAvailableCredit());
            user.setPassword("Test");
            user.setUserName(transaction.getUserName());
            users.add(user);
            System.out.println("Created User " + user.getUserName().trim() + " with user type " + user.getUserType());
        }


    }

    /**
     * Deletes a user with information from the transaction object and adds it into the list
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param users holds the list of users
     */
    public void delete(Transactions transaction, List<UserAccounts> users) {

        UserAccounts user = new UserAccounts();
        user.setUserType(transaction.getUserType());
        user.setAvailableCredit(transaction.getAvailableCredit());
        user.setUserName(transaction.getUserName());
        user.setPassword("passwords");
        if (users.removeIf(userAccounts -> (userAccounts.getUserName().equals(user.getUserName())))) {
            System.out.println("Deleted User " + user.getUserName().trim() + " with user type " + user.getUserType());
        }

    }

    /**
     * Adds the item to be advertised from the transactions object to the item list
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param items holds the list of items
     */
    public void advertise(Transactions transaction, List<AvailableItems> items) {

        AvailableItems item = new AvailableItems();

        item.setItemName(transaction.getItemName());
        item.setSellerName(transaction.getSellerName());
        item.setCurrentWinningBidder(transaction.getBuyerName());
        item.setNumberOfDaysLeft(transaction.getDaysToAuction());
        item.setCurrentWinningBidder("");
        item.setHighestBid(transaction.getMinBid());
        items.add(item);
        System.out.println("Advertised "+ item.getItemName().trim() + " with seller name " +  item.getSellerName());

    }

    /**
     * Adds the bid from the transaction object to the item list for current bid
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param items holds the list of items
     */
    public void bid(Transactions transaction, List<AvailableItems> items) {

        AvailableItems item = new AvailableItems();
        item.setItemName(transaction.getItemName());
        item.setSellerName(transaction.getSellerName());
        item.setCurrentWinningBidder(transaction.getBuyerName());
        item.setHighestBid(transaction.getNewBid());


        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getItemName().equals(item.getItemName()) && items.get(i).getSellerName().equals(item.getSellerName())){
                if(items.get(i).getHighestBid().compareTo(item.getHighestBid()) < 0) {
                    item.setNumberOfDaysLeft(items.get(i).getNumberOfDaysLeft());
                    items.set(i, item);
                    System.out.println("User "+ item.getCurrentWinningBidder().trim() + " bid " +  item.getHighestBid() + " on " + item.getItemName().trim());
                }
            }

        }

    }

    /**
     * Refund Credit with information from the transaction object and adds to the proper user
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param users holds the list of users
     */
    public void refund(Transactions transaction, List<UserAccounts> users) {
        boolean maxCreditCheck = true;
        boolean overCredit = true;
        UserAccounts buyer = new UserAccounts();
        UserAccounts seller = new UserAccounts();

        buyer.setUserName(transaction.getBuyerName());
        seller.setUserName(transaction.getSellerName());

        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(seller.getUserName())) {
                seller.setAvailableCredit(users.get(i).getAvailableCredit().subtract(transaction.getRefundCredit()));
                seller.setUserType(users.get(i).getUserType());
                seller.setPassword(users.get(i).getPassword());
                users.set(i, seller);
                System.out.println("Removed " + transaction.getRefundCredit() + " from seller " + seller.getUserName().trim() + " for refund");
            }
        }
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(buyer.getUserName())) {
                buyer.setAvailableCredit(users.get(i).getAvailableCredit().add(transaction.getRefundCredit()));
                buyer.setUserType(users.get(i).getUserType());
                buyer.setPassword(users.get(i).getPassword());
                users.set(i, buyer);
                System.out.println("Refunded " + transaction.getRefundCredit() + " to buyer " + buyer.getUserName().trim());
            }
        }


    }

    /**
     * Adds credit with information from the transaction object and adds it to the proper user
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param users holds the list of users
     */
    public void addCredit(Transactions transaction, List<UserAccounts> users){
        UserAccounts user = new UserAccounts();
        user.setUserType(transaction.getUserType());
        user.setUserName(transaction.getUserName());

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserName().equals(user.getUserName())){
                user.setAvailableCredit(users.get(i).getAvailableCredit().add(transaction.getAvailableCredit()));
                user.setPassword(users.get(i).getPassword());
                users.set(i, user);
                System.out.println("Added "+ transaction.getAvailableCredit() + " to user " +  user.getUserName().trim());
            }

        }

    }

    /**
     * Enables the user passed in from transaction and updates the users list
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param users holds the list of users
     */
    public void enable(Transactions transaction, List<UserAccounts> users){

        UserAccounts user = new UserAccounts();
        user.setAvailableCredit(transaction.getAvailableCredit());
        user.setUserName(transaction.getUserName());
        user.setUserType(transaction.getUserType());

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserName().equals(user.getUserName())){
                user.setPassword(users.get(i).getPassword());
                users.set(i, user);
                System.out.println("Enabled user "+ user.getUserName().trim() + " with user type " +  user.getUserType());
            }

        }

    }
    /**
     * Disables the user passed in from the transaction and updates the user list
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param users holds the list of users
     */
    public void disable(Transactions transaction, List<UserAccounts> users){

        UserAccounts user = new UserAccounts();
        user.setAvailableCredit(transaction.getAvailableCredit());
        user.setUserName(transaction.getUserName());

        for(int i = 0; i < users.size(); i++){
           if(users.get(i).getUserName().equals(user.getUserName())){
               user.setUserType(DISABLE_CODE);
               user.setPassword(users.get(i).getPassword());
               users.set(i, user);
               System.out.println("Disabled user "+ user.getUserName().trim());
            }

        }

    }

    /**
     * Decrements the number of days left on the auction and if 0 days left the auction is completed
     * @param items holds the items that we are decrementing number of days for
     */
    public void decrementAuctionDay(List<AvailableItems> items, List<UserAccounts> users) throws IOException{

        for(int i = 0; i < items.size(); i++){
            AvailableItems item = new AvailableItems();
            item.setItemName(items.get(i).getItemName());
            item.setCurrentWinningBidder(items.get(i).getCurrentWinningBidder());
            item.setHighestBid(items.get(i).getHighestBid());
            item.setSellerName(items.get(i).getSellerName());

            if(items.get(i).getNumberOfDaysLeft() != 0) {
                item.setNumberOfDaysLeft(items.get(i).getNumberOfDaysLeft() - 1);
                items.set(i, item);
                System.out.println("Decremented number of days by 1 on " + item.getItemName().trim());
            }else{
                completeAuction(items, item, users);
                //items list size decreases by 1 after complete auction so have to decrement counter as well
                i--;
            }
        }

    }

    /**
     * Completes an auction for when the number of days left is zero
     * @param items holds all the items in the liat
     * @param item the item we are completing the auction for
     * @param users holds the list of users
     */
    public void completeAuction(List<AvailableItems> items, AvailableItems item, List<UserAccounts> users) {
        boolean maxCreditCheck = true;
        boolean overCredit = true;
        boolean noBuyer = false;
        // compare the item with the user and update the available credit

            if(!item.getCurrentWinningBidder().trim().equals("")) {
                for (int i = 0; i < users.size(); i++) {
                    UserAccounts user = new UserAccounts();
                    user.setUserName(users.get(i).getUserName());
                    user.setUserType(users.get(i).getUserType());
                    user.setPassword(users.get(i).getPassword());
                    user.setAvailableCredit(users.get(i).getAvailableCredit());
                    // Seller gets money
                    if (user.getUserName().equals(item.getSellerName())) {
                        //check if seller can accept anymore credit
                        if (!user.getAvailableCredit().equals(MAX_CREDIT)) {
                            maxCreditCheck = false;
                            user.setAvailableCredit(user.getAvailableCredit().add(item.getHighestBid()));
                            // checks to see if the bid will put the seller over credit
                            if (user.getAvailableCredit().compareTo(MAX_CREDIT) < 0) {
                                overCredit = false;
                                users.set(i, user);
                                System.out.println("Seller " + item.getSellerName().trim() + " sold " + item.getItemName().trim() + " for " + item.getHighestBid());
                            } else {
                                System.out.println("The Complete Auction transaction on " + item.getItemName().trim()
                                        + " cannot go through as the bid will put the user over the max credit limit but " + item.getItemName().trim() + " is removed");
                            }
                        } else {
                            System.out.println("Sellers credit is already the max so the transaction cannot go through but item is removed");
                        }
                    }
                }
                for (int i = 0; i < users.size(); i++) {
                    UserAccounts user = new UserAccounts();
                    user.setUserName(users.get(i).getUserName());
                    user.setUserType(users.get(i).getUserType());
                    user.setPassword(users.get(i).getPassword());
                    user.setAvailableCredit(users.get(i).getAvailableCredit());
                    // Buyer loses money
                    if (user.getUserName().equals(item.getCurrentWinningBidder())) {
                        if (!maxCreditCheck && !overCredit) {
                            user.setAvailableCredit(user.getAvailableCredit().subtract(item.getHighestBid()));
                            users.set(i, user);
                            System.out.println("Buyer " + item.getCurrentWinningBidder().trim() + " bought " + item.getItemName().trim() + " for " + item.getHighestBid());
                        }
                    }
                }
            }else{
                noBuyer = true;
            }



        // remove the item from the list after transaction
        if (items.removeIf(itemList -> (itemList.getItemName().equals(item.getItemName())))){
            if(noBuyer){
                System.out.println("Completed Auction for " + item.getItemName().trim() + " but had no buyer");
            }else {
                System.out.println("Completed Auction for " + item.getItemName().trim());
            }

        }

    }

    /**
     * Check if any deleted users have the highest bid on an item and remove them from item and put highest bid to 0
     * @param users holds the users in a list
     * @param items holds all the items in a list
     */
    public void checkForDeletedUsers(List<UserAccounts> users, List<AvailableItems> items){

        for (AvailableItems availableItems : items) {
            boolean check = false;
            AvailableItems item = new AvailableItems();
            item.setCurrentWinningBidder(availableItems.getCurrentWinningBidder());

            for (UserAccounts user : users) {
                if (user.getUserName().trim().equals(item.getCurrentWinningBidder().trim())) {
                    check = true;
                    break;
                }
            }

            if(!check){
                availableItems.setHighestBid(new BigDecimal("0.00"));
                availableItems.setCurrentWinningBidder("");
            }

        }

    }


}
