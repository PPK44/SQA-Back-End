package com.BackEnd;
import java.io.*;
import java.math.BigDecimal;
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
    String localDir = System.getProperty("user.dir");
    File availableItemsFile = new File(localDir + "\\items.if.txt");
    File userAccountsFile = new File(localDir + "\\current_user_accounts_file.txt");
    //File transactionFile = new File("transactions.txt");


    /**
     * Reads in transaction data from the daily transaction file, and converts the raw data into the Transactions datatype.
     * @param transactions TODO
     * @return a list of all Transactions
     */
    public List<Transactions> parseTransactions(List<Transactions> transactions) throws FileNotFoundException {
//        Scanner scanner = new Scanner(transactionFile);
//        while(scanner.hasNextLine()){
//            String line = scanner.nextLine();
//            //break everything up
//        }
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
            AvailableItems item = new AvailableItems();
            String line = scanner.nextLine();
            item.setItemName(line.substring(0, 25)); // Item Name
            item.setSellerName(line.substring(26, 41)); // Seller name
            item.setCurrentWinningBidder(line.substring(42, 57));  // Current Bidder's name
            item.setNumberOfDaysLeft(Integer.parseInt(line.substring(58, 61))); // Remaining days
            item.setHighestBid(new BigDecimal(line.substring(62, 68))); // Current bid
            items.add(item);
        }
        return items;
    }

    /**
     * Reads in user data from the user accounts file, and converts the raw data into the UserAccounts datatype.
     * @param users TODO
     * @return a list of all UserAccounts
     */
    public List<UserAccounts> parseUsers(List<UserAccounts> users) throws FileNotFoundException {

        Scanner scanner = new Scanner(userAccountsFile);
        while(scanner.hasNextLine()){
            UserAccounts user = new UserAccounts();
            String line = scanner.nextLine();
            user.setUserName(line.substring(0,15));
            user.setPassword(line.substring(16, 28));
            user.setUserType(line.substring(29, 31));
            user.setAvailableCredit(new BigDecimal(line.substring(32, 41)));
            users.add(user);
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
