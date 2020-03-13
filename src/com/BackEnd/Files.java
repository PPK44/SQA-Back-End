package com.BackEnd;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.BackEnd.AvailableItems;
import com.BackEnd.UserAccounts;
import com.BackEnd.FileIO;
import com.BackEnd.Transactions;

/**
 * This class handles file storage. It utilizes the FileIO class for file input.
 * It accesses three files: the user accounts file, the available items file, and the daily transaction file.
 */
public class Files {

    // File data stored as List datatype
    List<UserAccounts> users = new ArrayList<>();
    List<AvailableItems> items = new ArrayList<>();
    List<Transactions> transactions = new ArrayList<>();

    FileIO parser = new FileIO();

    /**
     * Updates, stores, and returns the daily transaction list, utilizing the FileIO class.
     * @return a list of all previous Transactions stored in the daily transaction file
     * @throws FileNotFoundException if the transaction file is missing
     */
    public List<Transactions> updateTransactionList() throws FileNotFoundException {
        transactions = parser.parseTransactions(transactions);
        return transactions;
    }

    /**
     * Updates and stores the list of users, utilizing the FileIO class.
     */
    public void updateUserList() throws FileNotFoundException {
        users = parser.parseUsers(users);
    }

    /**
     * Updates and stores the available item list, utilizing the FileIO class.
     */
    public void updateAvailableItemsList() throws FileNotFoundException {
        items = parser.parseItems(items);
    }

}
