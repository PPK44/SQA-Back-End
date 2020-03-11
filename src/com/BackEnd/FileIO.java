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
    File availableItemsFile = new File("availableitems.txt");
    File userAccountsFile = new File("useraccounts.txt");
    File transactionFile = new File("transactions.txt");


    /**
     *
     * @param transactions
     * @return
     */
    public List<Transactions> parseTransactions(List<Transactions> transactions) throws FileNotFoundException {
        Scanner scanner = new Scanner(transactionFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            //break everything up
        }


        return transactions;
    }

    /**
     *
     * @param items
     * @return
     */
    public List<AvailableItems> parseItems(List<AvailableItems> items) throws FileNotFoundException {
        Scanner scanner = new Scanner(availableItemsFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            //break everything up
        }


        return items;
    }

    /**
     *
     * @param users
     * @return
     */
    public List<UserAccounts> parseUsers(List<UserAccounts>users) throws FileNotFoundException {
        Scanner scanner = new Scanner(userAccountsFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            //break everything up
        }



        return users;
    }

    /**
     *
     * @param users
     */
    public void writeUserFile(List<UserAccounts> users){

    }

    /**
     *
     * @param items
     */
    public void writeItemFile(List<AvailableItems> items){

    }


}
