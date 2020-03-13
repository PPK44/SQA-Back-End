package com.BackEnd;
import java.io.*;
import java.util.*;
import com.BackEnd.AvailableItems;
import com.BackEnd.UserAccounts;
import com.BackEnd.Transactions;

/**
 * This class handles File IO for item storage, user account storage, and transaction storage.
 * It is used in conjunction with the Files class to make this data accessible to the backend project.
 */
public class FileIO{

    // Data for all three files accessed: the available items file, the user accounts file, and the daily transaction file.
    File availableItemsFile = new File("availableitems.txt");
    File userAccountsFile = new File("useraccounts.txt");
    File transactionFile = new File("transactions.txt");


    /**
     * Reads in transaction data from the daily transaction file, and converts the raw data into the Transactions datatype.
     * @param transactions TODO
     * @return a list of all Transactions
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
     * Reads in item data from the available items file, and converts the raw data into the AvailableItems datatype.
     * @param items TODO
     * @return a list of all AvailableItems
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
     * Reads in user data from the user accounts file, and converts the raw data into the UserAccounts datatype.
     * @param users TODO
     * @return a list of all UserAccounts
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
     * Writes the current userlist to the user accounts file.
     * @param users the userlist to write to file.
     */
    public void writeUserFile(List<UserAccounts> users){

    }

    /**
     * Writes the current available items list to the available items file.
     * @param items the available items list to write to file.
     */
    public void writeItemFile(List<AvailableItems> items){

    }


}
