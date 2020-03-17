package com.BackEnd;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.BackEnd.AvailableItems;
import com.BackEnd.UserAccounts;
import com.BackEnd.FileIO;
import com.BackEnd.Transactions;

/**
 * This class handles file storage an updating files. It utilizes the FileIO class for file input.
 * It accesses three files: the user accounts file, the available items file, and the daily transaction file.
 */
public class Files {

    // File data stored as List datatype
    List<UserAccounts> users = new ArrayList<>();
    List<AvailableItems> items = new ArrayList<>();
    List<Transactions> transactions = new ArrayList<>();
    UserAccounts user = new UserAccounts();

    FileIO parser = new FileIO();

    /**
     * Updates, stores, and returns the daily transaction list, utilizing the FileIO class
     * @throws FileNotFoundException if the transaction file is missing
     */
    public void updateTransactionList() throws FileNotFoundException {
        transactions = parser.parseTransactions(transactions);
        System.out.println(transactions.size());
        for (Transactions transaction: transactions) {
            if(transaction.getTransactionCode() == 03){
                StringBuilder sb = new StringBuilder(String.valueOf(transaction.getTransactionCode()));
                StringBuilder sb2 = new StringBuilder(String.valueOf(transaction.getMinBid()));
                System.out.print(sb.insert(0, "0", 0, 2 - sb.length()) + " ");
                System.out.print(transaction.getItemName() + " ");
                System.out.print(transaction.getUserName() + " ");
                System.out.print(transaction.getDaysToAuction() + " ");
                System.out.println(sb2.insert(0, "00", 0, 6 - sb2.length()));
            }

        }

    }

    /**
     * Updates and stores the list of users, utilizing the FileIO class.
     * @throws FileNotFoundException if the user file is missing
     */
    public void updateUserList() throws FileNotFoundException {
        users = parser.parseUsers(users);
        System.out.println(users.size());
        for (UserAccounts userAccounts : users) {
            System.out.print(userAccounts.getUserName() + " ");
            System.out.print(userAccounts.getPassword() + " ");
            System.out.print(userAccounts.getUserType() + " ");
            System.out.println(userAccounts.getAvailableCredit());
        }

    }

    /**
     * Updates and stores the available item list, utilizing the FileIO class.
     * @throws FileNotFoundException if the items file is missing
     */
    public void updateAvailableItemsList() throws FileNotFoundException {
        items = parser.parseItems(items);
        System.out.println(items.size());
        for (AvailableItems item : items) {
            System.out.print(item.getItemName() + " ");
            System.out.print(item.getSellerName() + " ");
            System.out.print(item.getCurrentWinningBidder() + " ");
            System.out.print(item.getNumberOfDaysLeft() + " ");
            System.out.println(item.getHighestBid());
        }
    }

    /**
     * Creates a new user with information from the transaction and adds it into the list
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void create(Transactions transaction){

    }

    /**
     * Deletes a user with information from the transaction object and adds it into the list
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void delete(Transactions transaction){

    }

    /**
     * Adds the item to be advertised from the transactions object to the item list
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void advertise(Transactions transaction){

    }

    /**
     * Adds the bid from the transaction object to the item list for current bid
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void bid(Transactions transaction){

    }

    /**
     * Refund Credit with information from the transaction object and adds to the proper user
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void refund(Transactions transaction){

    }

    /**
     * Adds credit with information from the transaction object and adds it to the proper user
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void addCredit(Transactions transaction){

    }

}
