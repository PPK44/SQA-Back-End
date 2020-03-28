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
 * It accesses three files: the user accounts file, the available items file, and the daily transaction file.
 */
public class Files {

    // File data stored as List datatype


    final String DISABLE_CODE = "DS";
    final BigDecimal MAX_CREDIT = new BigDecimal("999999.99");

    /**
     * Updates and stores the list of users, utilizing the FileIO class.
     * @throws FileNotFoundException if the user file is missing
     */
    public void updateUserList(List<UserAccounts> users, List<Transactions> transactions) throws IOException {

        for (Transactions transaction: transactions) {
            switch (transaction.getTransactionCode()) {
                case 1: //Create
                    create(transaction, users);
                    break;
                case 2:  //Delete
                    //delete(transaction, users);
                    break;
                case 5: //Refund
                    //refund(transaction, users);
                    break;
                case 6:  //Add credit
                    //addCredit(transaction, users);
                    break;
                case 7: //Enable
                    //enable(transaction, users);
                    break;
                case 8: //Disable
                    //disable(transaction, users);
                    break;
            }
        }

    }


    /**
     * Updates and stores the available item list, utilizing the FileIO class.
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
     */
    public void create(Transactions transaction, List<UserAccounts> users) {
        boolean check = false;

        for (UserAccounts userAccounts : users) {
            if (userAccounts.getUserName().equals(transaction.getUserName())) {
                check = true;
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
        }


    }

    /**
     * Deletes a user with information from the transaction object and adds it into the list
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void delete(Transactions transaction, List<UserAccounts> users) {

        UserAccounts user = new UserAccounts();
        user.setUserType(transaction.getUserType());
        user.setAvailableCredit(transaction.getAvailableCredit());
        user.setUserName(transaction.getUserName());
        user.setPassword("passwords");
        users.removeIf(userAccounts -> (userAccounts.getUserName().equals(user.getUserName())));


    }

    /**
     * Adds the item to be advertised from the transactions object to the item list
     * @param transaction holds the transaction that will be used to change/add to the list
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

    }

    /**
     * Adds the bid from the transaction object to the item list for current bid
     * @param transaction holds the transaction that will be used to change/add to the list
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
                }
            }

        }

    }

    /**
     * Refund Credit with information from the transaction object and adds to the proper user
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param users
     */
    public void refund(Transactions transaction, List<UserAccounts> users) {
        UserAccounts buyer = new UserAccounts();
        UserAccounts seller = new UserAccounts();

        buyer.setUserName(transaction.getBuyerName());
        seller.setUserName(transaction.getSellerName());

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserName().equals(seller.getUserName())){
                seller.setAvailableCredit(users.get(i).getAvailableCredit().subtract(transaction.getRefundCredit()));
                seller.setUserType(users.get(i).getUserType());
                seller.setPassword(users.get(i).getPassword());
                users.set(i, seller);

            }
            if(users.get(i).getUserName().equals(buyer.getUserName())){
                buyer.setAvailableCredit(users.get(i).getAvailableCredit().add(transaction.getRefundCredit()));
                buyer.setUserType(users.get(i).getUserType());
                buyer.setPassword(users.get(i).getPassword());
                users.set(i, buyer);
                
            }

        }
    }

    /**
     * Adds credit with information from the transaction object and adds it to the proper user
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param users
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
            }

        }

    }

    /**
     * Enables the user passed in from transaction and updates the users list
     * @param transaction holds the transaction that will be used to change/add to the list
     * @param users
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
            }

        }

    }

    /**
     * Decrements the number of days left on the auction and if 0 days left the auction is completed
     * STILL NEED TO TEST
     * @param items
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
            }else{
                completeAuction(items, item, users);
            }
        }

    }

    public void completeAuction(List<AvailableItems>items, AvailableItems item, List<UserAccounts> users) {

        // compare the item with the user and update the available credit
        for (int i = 0; i < users.size(); i++) {
            UserAccounts user = new UserAccounts();
            user.setUserName(users.get(i).getUserName());
            user.setUserType(users.get(i).getUserType());
            user.setPassword(users.get(i).getPassword());
            user.setAvailableCredit(users.get(i).getAvailableCredit());



            // Seller gets money
            if(user.getUserName().equals(item.getSellerName())) {
                user.setAvailableCredit(user.getAvailableCredit().add(item.getHighestBid()));
                users.set(i, user);
                // Buyer loses money
                if(user.getUserName().equals(item.getCurrentWinningBidder())) {
                    user.setAvailableCredit(user.getAvailableCredit().subtract(item.getHighestBid()));
                    users.set(i, user);
                }

            }

        }

        // remove the item from the list after transaction
        items.removeIf(itemList -> (itemList.getItemName().equals(item.getItemName())));

    }


}
