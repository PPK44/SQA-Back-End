package com.BackEnd;
import java.io.*;
import java.util.*;
import com.BackEnd.AvailableItems;
import com.BackEnd.UserAccounts;
import com.BackEnd.Transactions;

/**
 *
 */
public class FileIO{

    /**
     *
     */
    File availableItemsFile = new File("");
    File userAccountsFile = new File("");
    File transactionFile = new File("");


    /**
     *
     * @param transactions
     * @return
     */
    List<Transactions> ParseTransactions(List<Transactions> transactions){


        return transactions;
    }

    /**
     *
     * @param items
     * @return
     */
    List<AvailableItems> ParseItems(List<AvailableItems> items){


        return items;
    }

    /**
     *
     * @param users
     * @return
     */
    List<UserAccounts> ParseUsers(List<UserAccounts>users){



        return users;
    }

    /**
     *
     * @param users
     */
    void WriteUserFile(List<UserAccounts> users){

    }

    /**
     *
     * @param items
     */
    void WriteItemFile(List<AvailableItems> items){

    }


}
