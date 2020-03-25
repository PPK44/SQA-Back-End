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
    public void updateAvailableItemsList() throws IOException {
        items = parser.parseItems(items);
        for (Transactions transaction: transactions) {

            switch (transaction.getTransactionCode()) {
                case 3:  //Advertise
                    advertise(transaction, items);
                    break;
                case 4: //Bid
                    bid(transaction, items);
                    break;
                case 5: //Refund
                    //refund(transaction, items);
                    break;
            }
        }
    }

    /**
     * Creates a new user with information from the transaction and adds it into the list
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void create(Transactions transaction, List<UserAccounts> users) throws IOException {
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
    public void delete(Transactions transaction, List<UserAccounts> users) throws IOException {

        UserAccounts user = new UserAccounts();
        user.setUserType(transaction.getUserType());
        user.setAvailableCredit(transaction.getAvailableCredit());
        user.setUserName(transaction.getUserName());
        user.setPassword("passwords");
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
    public void advertise(Transactions transaction, List<AvailableItems> items) throws IOException {

        AvailableItems item = new AvailableItems();

        item.setItemName(transaction.getItemName());
        item.setSellerName(transaction.getSellerName());
        item.setCurrentWinningBidder(transaction.getBuyerName());
        item.setNumberOfDaysLeft(transaction.getDaysToAuction());
        item.setCurrentWinningBidder("");
        item.setHighestBid(transaction.getMinBid());

        items.add(item);

        parser.writeItemFile(items);

    }

    /**
     * Adds the bid from the transaction object to the item list for current bid
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void bid(Transactions transaction, List<AvailableItems> items) throws IOException {

        AvailableItems item = new AvailableItems();
        item.setItemName(transaction.getItemName());
        item.setSellerName(transaction.getSellerName());
        item.setCurrentWinningBidder(transaction.getBuyerName());
        item.setHighestBid(transaction.getNewBid());


        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getItemName().equals(item.getItemName()) && items.get(i).getSellerName().equals(item.getSellerName())){
                item.setNumberOfDaysLeft(items.get(i).getNumberOfDaysLeft());
                item.setHighestBid(transaction.getHighestBid());
                items.set(i, item);
            }

        }

        parser.writeItemFile(items);

    }

    /**
     * Refund Credit with information from the transaction object and adds to the proper user
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void refund(Transactions transaction, List<UserAccounts> users) throws IOException {
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
        parser.writeUserFile(users);
    }

    /**
     * Adds credit with information from the transaction object and adds it to the proper user
     * @param transaction holds the transaction that will be used to change/add to the list
     */
    public void addCredit(Transactions transaction, List<UserAccounts> users) throws IOException {
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
