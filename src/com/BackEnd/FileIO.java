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

    /**
     * Reads in transaction data from the daily transaction file, and converts the raw data into the Transactions datatype.
     * @return a list of all Transactions
     */
    public List<Transactions> parseTransactions(File transactionFile) throws FileNotFoundException {
        List<Transactions> transactions = new ArrayList<>();
        Scanner scanner = new Scanner(transactionFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            Transactions transaction = new Transactions();

           switch (line.substring(0, 2)) {
               case "01": //Create
               case "02":  //Delete
               case "06":  //Add credit
               case "07": //Enable
               case "08": //Disable
                   transaction.setTransactionCode(Integer.parseInt(line.substring(0, 2)));
                   transaction.setUserName(line.substring(3, 18));
                   transaction.setUserType(line.substring(19, 21));
                   transaction.setAvailableCredit(new BigDecimal(line.substring(22, 31)));
                   transactions.add(transaction);
                   break;
               case "03":  //Advertise
                   transaction.setTransactionCode(Integer.parseInt(line.substring(0, 2)));
                   transaction.setItemName(line.substring(3, 22));
                   transaction.setSellerName(line.substring(23, 38));
                   transaction.setDaysToAuction(Integer.parseInt(line.substring(39, 42)));
                   transaction.setMinBid(new BigDecimal(line.substring(43, 49)));
                   transactions.add(transaction);
                   break;
               case "04": //Bid
                   transaction.setTransactionCode(Integer.parseInt(line.substring(0, 2)));
                   transaction.setItemName(line.substring(3, 22));
                   transaction.setSellerName(line.substring(23, 38));
                   transaction.setBuyerName(line.substring(39, 54));
                   transaction.setNewBid(new BigDecimal(line.substring(55, 61)));
                   transactions.add(transaction);
                   break;
               case "05": //Refund
                    transaction.setTransactionCode(Integer.parseInt(line.substring(0, 2)));
                    transaction.setBuyerName(line.substring(3, 18));
                    transaction.setSellerName(line.substring(19, 34));
                    transaction.setRefundCredit(new BigDecimal(line.substring(35, 41)));
                    transactions.add(transaction);
                   break;
           }
       }
        return transactions;
    }


    /**
     * Reads in item data from the available items file, and converts the raw data into the AvailableItems datatype.
     * @return a list of all AvailableItems
     */
    public List<AvailableItems> parseItems(File availableItemsFile) throws FileNotFoundException {
        List<AvailableItems> items = new ArrayList<>();
        Scanner scanner = new Scanner(availableItemsFile);
        while(scanner.hasNextLine()){
            AvailableItems item = new AvailableItems();
            String line = scanner.nextLine();
            item.setTransactionCode(Integer.parseInt(line.substring(0, 4).trim()));
            item.setItemName(line.substring(5, 24));
            item.setSellerName(line.substring(25, 40));
            item.setCurrentWinningBidder(line.substring(41, 56));
            item.setNumberOfDaysLeft(Integer.parseInt(line.substring(57, 60)));
            item.setHighestBid(new BigDecimal(line.substring(61, 67)));
            items.add(item);
        }
        return items;
    }



    /**
     * Reads in user data from the user accounts file, and converts the raw data into the UserAccounts datatype.
     * @return a list of all UserAccounts
     */
    public List<UserAccounts> parseUsers(File userAccountsFile) throws FileNotFoundException {
        List<UserAccounts> users = new ArrayList<>();
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
    public void writeUserFile(List<UserAccounts> users, String file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for(UserAccounts user : users){
            writer.write(user.toString());
        }
        writer.close();
    }

    /**
     * Writes the current available items list to the available items file.
     * @param items the available items list to write to file.
     */
    public void writeItemFile(List<AvailableItems> items, String file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for(AvailableItems item : items){
            writer.write(item.toString());
        }
        writer.close();
    }


}
