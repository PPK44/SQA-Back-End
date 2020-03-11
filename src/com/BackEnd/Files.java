package com.BackEnd;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.BackEnd.AvailableItems;
import com.BackEnd.UserAccounts;
import com.BackEnd.FileIO;
import com.BackEnd.Transactions;

public class Files {

    /**
     *
     */
    List<UserAccounts> users = new ArrayList<>();
    List<AvailableItems> items = new ArrayList<>();
    List<Transactions> transactions = new ArrayList<>();
    FileIO parser = new FileIO();

    public List<Transactions> updateTransactionList() throws FileNotFoundException {
        transactions = parser.parseTransactions(transactions);

        return transactions;
    }

    /**
     *
     */
    public void updateUserList() throws FileNotFoundException {
        users = parser.parseUsers(users);



    }

    /**
     *
     */
    public void updateAvailableItemsList() throws FileNotFoundException {
        items = parser.parseItems(items);

    }



}
