package com.BackEnd;
import java.io.*;
import java.math.BigDecimal;
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


    FileIO parser = new FileIO();

    /**
     * Updates, stores, and returns the daily transaction list, utilizing the FileIO class
     * @throws FileNotFoundException if the transaction file is missing
     */
    public void updateTransactionList() throws FileNotFoundException {
        transactions = parser.parseTransactions(transactions);

    }

    /**
     * Updates and stores the list of users, utilizing the FileIO class.
     * @throws FileNotFoundException if the user file is missing
     */
    public void updateUserList() throws IOException {
        users = parser.parseUsers(users);
        for (Transactions transaction: transactions) {

            switch (transaction.getTransactionCode()) {
                case 1: //Create
                    //create(transaction);
                    break;
                case 2:  //Delete
                    //delete(transaction);
                    break;
                case 3:  //Advertise
                    advertise(transaction);
                    break;
                case 4: //Bid
                    bid(transaction);
                    break;
                case 5: //Refund
                    refund(transaction);
                    break;
                case 6:  //Add credit
                    addCredit(transaction);
                    break;
                case 7: //Enable

                    break;
                case 8: //Disable

                    break;
            }
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
    public void create(Transactions transaction) throws IOException {
        UserAccounts user = new UserAccounts();
        user.setUserType(transaction.getUserType());
        // do sb on write to file not now stupid paul
        user.setAvailableCredit(transaction.getAvailableCredit());
        user.setPassword("Test");
        user.setUserName(transaction.getUserName());
        users.add(user);

        parser.writeUserFile(users);

    }

    /**
     * Deletes a user with information from the transaction object and adds it into the list
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void delete(Transactions transaction) throws IOException {

        UserAccounts user = new UserAccounts();
        user.setUserType(transaction.getUserType());
        user.setAvailableCredit(transaction.getAvailableCredit());
        user.setUserName(transaction.getUserName());
        user.setPassword("buyinggf20k");
        users.removeIf(userAccounts -> (userAccounts.getUserName().equals(user.getUserName())));
        for (UserAccounts user1 : users) {
            System.out.print(user1.getUserName() + " ");
            System.out.print(user1.getUserType() + " ");
            System.out.println(user1.getAvailableCredit());
        }
        parser.writeUserFile(users);

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
    public void addCredit(Transactions transaction) throws IOException {
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
        parser.writeUserFile(users);

    }

}
