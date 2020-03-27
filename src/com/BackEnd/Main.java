package com.BackEnd;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.BackEnd.Files;
import com.BackEnd.FileIO;

/**
 * This backend project updates account and item data for an auction system on a nightly basis.
 * It accesses and manipulates a user accounts file, an available items file, and a daily transaction file.
 * @author Paul Kerrigan, Gajan Sivanesan, Geerthan Srikantharajah
 * @version 1.0
 */
public class Main extends FileIO {

    public static void main(String[] args) throws IOException {

        // Files instance for storing accounts/items/transactions files
        Files run = new Files();
        FileIO parser = new FileIO();

        List<Transactions> transactions = parser.parseTransactions();
        List<AvailableItems> items = parser.parseItems();
        List<UserAccounts> users = parser.parseUsers();
        //run.updateUserList(users, transactions);
        run.updateAvailableItemsList(items, transactions);
        //run.decrementAuctionDay(items, users);
        parser.writeItemFile(items);
        //parser.writeUserFile(users);


    }

}
