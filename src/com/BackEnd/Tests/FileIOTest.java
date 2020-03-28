package com.BackEnd.Tests;

import com.BackEnd.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class FileIOTest {

    // Data for all three files accessed: the available items file, the user accounts file, and the daily transaction file.
    String localDir = System.getProperty("user.dir");
    File availableItemsFile = new File(localDir + "\\FileIOTestFiles\\testitems.if.txt");
    File userAccountsFile = new File(localDir + "\\FileIOTestFiles\\testcurrent_user_accounts_file.txt");
    File transactionFile = new File(localDir + "\\FileIOTestFiles\\testDailyTransactionFile.txt");

    // Linux Paths
//    String localDir = System.getProperty("user.dir");
//    File availableItemsFile = new File(localDir + "/items.if.txt");
//    File userAccountsFile = new File(localDir + "/current_user_accounts_file.txt");
//    File transactionFile = new File(localDir + "/daily_transaction_file.txt");

    FileIO fileIO = new FileIO();

    Transactions createTransaction = new Transactions();
    Transactions deleteTransaction = new Transactions();
    Transactions advertiseTransaction = new Transactions();
    Transactions bidTransaction = new Transactions();
    Transactions refundTransaction = new Transactions();
    Transactions addCreditTransaction = new Transactions();
    List<Transactions> testTransactions = new ArrayList<>();

    AvailableItems item = new AvailableItems();
    List<AvailableItems> items = new ArrayList<>();

    UserAccounts user = new UserAccounts();
    List<UserAccounts> users = new ArrayList<>();



    @Before
    public void setUp() throws Exception {

        // Prep create test data
        createTransaction.setTransactionCode(01);
        createTransaction.setUserName("MR.RUNESCAPEMAN");
        createTransaction.setUserType("FS");
        createTransaction.setAvailableCredit(new BigDecimal("00.22"));
        testTransactions.add(createTransaction);

        // Prep Delete test data
        deleteTransaction.setTransactionCode(02);
        deleteTransaction.setUserName("MR.RUNESCAPEMAN");
        deleteTransaction.setUserType("FS");
        deleteTransaction.setAvailableCredit(new BigDecimal("00.22"));
        testTransactions.add(deleteTransaction);

        // Prep Advertise test data
        advertiseTransaction.setTransactionCode(03);
        advertiseTransaction.setItemName("DRAGON SCIMITAR    ");
        advertiseTransaction.setSellerName("MR.RUNESCAPEMAN");
        advertiseTransaction.setDaysToAuction(100);
        advertiseTransaction.setMinBid(new BigDecimal("80.99"));
        testTransactions.add(advertiseTransaction);

        // Prep Bid test data
        bidTransaction.setTransactionCode(04);
        bidTransaction.setItemName("DRAGON SCIMITAR    ");
        bidTransaction.setSellerName("Paul           ");
        bidTransaction.setBuyerName("HECKING COOLMAN");
        bidTransaction.setNewBid(new BigDecimal("199.99"));
        testTransactions.add(bidTransaction);

        // Prep Refund test data
        refundTransaction.setTransactionCode(05);
        refundTransaction.setBuyerName("HECKINGCOOLMAN ");
        refundTransaction.setSellerName("MR.RUNESCAPEMAN");
        refundTransaction.setRefundCredit(new BigDecimal("199.99"));
        testTransactions.add(refundTransaction);

        // Prep Add Credit Test Data
        addCreditTransaction.setTransactionCode(06);
        addCreditTransaction.setUserName("MR.RUNESCAPEMAN");
        addCreditTransaction.setUserType("FS");
        addCreditTransaction.setAvailableCredit(new BigDecimal("10000.00"));
        testTransactions.add(addCreditTransaction);

        // Prep Item Test Data
        item.setItemName("apple              ");
        item.setSellerName("test123        ");
        item.setCurrentWinningBidder("BUYINGMAN      ");
        item.setNumberOfDaysLeft(20);
        item.setHighestBid(new BigDecimal("9.00"));
        items.add(item);

        // Prep User Test Data
        user.setUserName("MR.RUNESCAPEMAN");
        user.setPassword("buyinggf20k ");
        user.setUserType("FS");
        user.setAvailableCredit(new BigDecimal("00.44"));
        users.add(user);

    }

    @Test
    public void parseTransactions() throws FileNotFoundException {
        List<Transactions> parsedTransaction =  fileIO.parseTransactions(transactionFile);

        // Test if the File is being read
        assertEquals(20, parsedTransaction.size());

        // Check if Create is parsed correctly (01)
        assertEquals(testTransactions.get(0).getTransactionCode(), parsedTransaction.get(0).getTransactionCode());
        assertEquals(testTransactions.get(0).getUserName(), parsedTransaction.get(0).getUserName());
        assertEquals(testTransactions.get(0).getUserType(), parsedTransaction.get(0).getUserType());
        assertEquals(testTransactions.get(0).getAvailableCredit(), parsedTransaction.get(0).getAvailableCredit());

        // Check if Delete is parsed correctly (02)
        assertEquals(testTransactions.get(1).getTransactionCode(), parsedTransaction.get(2).getTransactionCode());
        assertEquals(testTransactions.get(1).getUserName(), parsedTransaction.get(2).getUserName());
        assertEquals(testTransactions.get(1).getUserType(), parsedTransaction.get(2).getUserType());
        assertEquals(testTransactions.get(1).getAvailableCredit(), parsedTransaction.get(2).getAvailableCredit());

        // Check if Advertise is parsed correctly (03)
        assertEquals(testTransactions.get(2).getTransactionCode(), parsedTransaction.get(5).getTransactionCode());
        assertEquals(testTransactions.get(2).getItemName(), parsedTransaction.get(5).getItemName());
        assertEquals(testTransactions.get(2).getDaysToAuction(), parsedTransaction.get(5).getDaysToAuction());
        assertEquals(testTransactions.get(2).getSellerName(), parsedTransaction.get(5).getSellerName());
        assertEquals(testTransactions.get(2).getMinBid(), parsedTransaction.get(5).getMinBid());

        // Check if Bid is parsed correctly (04)
        assertEquals(testTransactions.get(3).getTransactionCode(), parsedTransaction.get(18).getTransactionCode());
        assertEquals(testTransactions.get(3).getItemName(), parsedTransaction.get(18).getItemName());
        assertEquals(testTransactions.get(3).getBuyerName(), parsedTransaction.get(18).getBuyerName());
        assertEquals(testTransactions.get(3).getSellerName(), parsedTransaction.get(18).getSellerName());
        assertEquals(testTransactions.get(3).getNewBid(), parsedTransaction.get(18).getNewBid());

        // Check if Refund is parsed correctly (05)
        assertEquals(testTransactions.get(4).getTransactionCode(), parsedTransaction.get(15).getTransactionCode());
        assertEquals(testTransactions.get(4).getBuyerName(), parsedTransaction.get(15).getBuyerName());
        assertEquals(testTransactions.get(4).getSellerName(), parsedTransaction.get(15).getSellerName());
        assertEquals(testTransactions.get(4).getRefundCredit(), parsedTransaction.get(15).getRefundCredit());

        // Check if Add Credit is parsed correctly (06)
        assertEquals(testTransactions.get(5).getTransactionCode(), parsedTransaction.get(3).getTransactionCode());
        assertEquals(testTransactions.get(5).getBuyerName(), parsedTransaction.get(3).getBuyerName());
        assertEquals(testTransactions.get(5).getUserType(), parsedTransaction.get(3).getUserType());
        assertEquals(testTransactions.get(5).getAvailableCredit(), parsedTransaction.get(3).getAvailableCredit());

    }

    @Test
    public void parseItems() throws FileNotFoundException{
        List<AvailableItems> parsedItems =  fileIO.parseItems(availableItemsFile);

        // Test if parser can read the file
        assertEquals(1, parsedItems.size());

        // Test if Item is parsed correctly
        assertEquals(items.get(0).getItemName(), parsedItems.get(0).getItemName());
        assertEquals(items.get(0).getSellerName(), parsedItems.get(0).getSellerName());
        assertEquals(items.get(0).getCurrentWinningBidder(), parsedItems.get(0).getCurrentWinningBidder());
        assertEquals(items.get(0).getHighestBid(), parsedItems.get(0).getHighestBid());
    }

    @Test
    public void parseUsers() throws FileNotFoundException{
        List<UserAccounts> parsedUsers = fileIO.parseUsers(userAccountsFile);

        // Test if parser can read the file
        assertEquals(1, parsedUsers.size());

        // Test id user is parsed correctly
        assertEquals(users.get(0).getUserName(), parsedUsers.get(0).getUserName());
        assertEquals(users.get(0).getPassword(), parsedUsers.get(0).getPassword());
        assertEquals(users.get(0).getAvailableCredit(), parsedUsers.get(0).getAvailableCredit());
        assertEquals(users.get(0).getUserType(), parsedUsers.get(0).getUserType());

    }

    @Test
    public void writeUserFile() throws IOException {
        fileIO.writeUserFile(users, userAccountsFile.toString());
        String expected = "MR.RUNESCAPEMAN buyinggf20k  FS 000000.44";
        // Linux Path
//        File userFileTest = new File(localDir+ "/FileIOTestFiles/testcurrent_user_accounts_file.txt");

        //Windows Path
        File userFileTest = new File(localDir+ "\\FileIOTestFiles\\testcurrent_user_accounts_file.txt");
        Scanner scanner = new Scanner(userFileTest);
        String actualString = scanner.nextLine();
        assertEquals(expected, actualString);

    }

    @Test
    public void writeItemFile() throws IOException {
        fileIO.writeItemFile(items, availableItemsFile.toString());
        String expected = "apple               test123         BUYINGMAN       020 009.00";
        // Linux Path
//        File itemFileTest = new File(localDir+ "/FileIOTestFiles/testItems.if.txt");

        // Windows Path
        File itemFileTest = new File(localDir+ "\\FileIOTestFiles\\testItems.if.txt");
        Scanner scanner = new Scanner(itemFileTest);
        String actualString = scanner.nextLine();
        assertEquals(expected, actualString);

    }
}