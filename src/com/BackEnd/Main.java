package com.BackEnd;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

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
        //parser instance to read/ write from the files
        FileIO parser = new FileIO();
        // Data for all three files accessed: the available items file, the user accounts file, and the daily transaction file.
        String localDir = System.getProperty("user.dir");
        File availableItemsFile = new File(localDir + File.separator + "items.if.txt");
        File userAccountsFile = new File(localDir + File.separator + "current_user_accounts_file.txt");
        File transactionFile = new File(localDir + File.separator + "daily_transaction_file.txt");


        List<Transactions> transactions = parser.parseTransactions(transactionFile);
        List<AvailableItems> items = parser.parseItems(availableItemsFile);
        List<UserAccounts> users = parser.parseUsers(userAccountsFile);
        run.updateUserList(users, transactions);
        run.updateAvailableItemsList(items, transactions);
        run.checkForDeletedUsers(users, items);
        run.decrementAuctionDay(items, users);
        parser.writeItemFile(items, availableItemsFile.toString());
        parser.writeUserFile(users, userAccountsFile.toString());


    }

}
