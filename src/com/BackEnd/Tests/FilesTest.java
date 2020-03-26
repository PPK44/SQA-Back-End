package com.BackEnd.Tests;

import com.BackEnd.Files;
import com.BackEnd.Transactions;

import com.BackEnd.UserAccounts;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Use junit4
 */
public class FilesTest {

    String filePrefix = "test_";
    String localDir = System.getProperty("user.dir");

    Files file = new Files();

    Transactions transaction = new Transactions();
    Transactions updateTransactions = new Transactions();

    @Before
    public void init(){
        List<Transactions> transactions = new ArrayList<>();
        transaction.setTransactionCode(1);
        transaction.setUserName("paul");
        transaction.setUserType("FS");
        transaction.setAvailableCredit(new BigDecimal("100.00"));
        transactions.add(transaction);
    }

    /**
     * Tests the updateTransactionList() method in Files by creating a test transaction list to verify.
     */
    @Test
    public void updateTransactionList() throws IOException {
        List<Transactions> testTransactionList = new ArrayList<>();

        Transactions createTransaction = new Transactions();
        createTransaction.setTransactionCode(1);
        createTransaction.setUserName("Joe");
        createTransaction.setUserType("FS");
        createTransaction.setAvailableCredit(new BigDecimal("500.00"));
        testTransactionList.add(createTransaction);

        Transactions createTransaction2 = new Transactions();
        createTransaction2.setTransactionCode(1);
        createTransaction2.setUserName("Barrett");
        createTransaction2.setUserType("AA");
        createTransaction2.setAvailableCredit(new BigDecimal("500.00"));
        testTransactionList.add(createTransaction2);

        Transactions addCreditTransaction = new Transactions();
        addCreditTransaction.setTransactionCode(6);
        addCreditTransaction.setUserName("Joe");
        addCreditTransaction.setUserType("FS");
        addCreditTransaction.setAvailableCredit(new BigDecimal("25.50"));
        testTransactionList.add(addCreditTransaction);

        Transactions addCreditTransaction2 = new Transactions();
        addCreditTransaction2.setTransactionCode(6);
        addCreditTransaction2.setUserName("Barrett");
        addCreditTransaction2.setUserType("AA");
        addCreditTransaction2.setAvailableCredit(new BigDecimal("50"));
        testTransactionList.add(addCreditTransaction2);

        Transactions advertiseTransaction = new Transactions();
        advertiseTransaction.setTransactionCode(3);
        advertiseTransaction.setItemName("Bread");
        advertiseTransaction.setSellerName("Joe");
        advertiseTransaction.setDaysToAuction(5);
        advertiseTransaction.setMinBid(new BigDecimal("149.99"));
        testTransactionList.add(advertiseTransaction);

        Transactions bidTransaction = new Transactions();
        bidTransaction.setTransactionCode(4);
        bidTransaction.setItemName("Bread");
        bidTransaction.setSellerName("Joe");
        bidTransaction.setBuyerName("Barrett");
        bidTransaction.setNewBid(new BigDecimal("150"));
        testTransactionList.add(bidTransaction);

        Transactions refundTransaction = new Transactions();
        refundTransaction.setTransactionCode(5);
        refundTransaction.setBuyerName("Barrett");
        refundTransaction.setSellerName("Joe");
        refundTransaction.setRefundCredit(new BigDecimal("150"));
        testTransactionList.add(refundTransaction);

        Transactions deleteTransaction = new Transactions();
        deleteTransaction.setTransactionCode(2);
        deleteTransaction.setBuyerName("Joe");
        deleteTransaction.setUserType("FS");
        testTransactionList.add(deleteTransaction);

        File testTransactionFile = new File(localDir + "\\" + filePrefix + "daily_transaction_file.txt");
        FileWriter testTransactionFW = new FileWriter(testTransactionFile);
        for(Transactions transaction: testTransactionList)
            testTransactionFW.write(transaction.toString());
        testTransactionFW.close();

        file.updateTransactionList(filePrefix);

        int transactionCount = 0;
        Scanner in = new Scanner(testTransactionFile);
        while(in.hasNextLine())
            transactionCount++;

        // May not be strong enough test
        assertEquals(transactionCount, 5); //Could be different
        //Need to add enable/disable
    }

    /**
     * Tests the updateUserList() method in Files by creating a test set of user-creating transactions to verify.
     */
    @Test
    public void updateUserList() throws IOException {
        List<Transactions> testTransactionList = new ArrayList<>();

        Transactions createTransaction = new Transactions();
        createTransaction.setTransactionCode(1);
        createTransaction.setUserName("Joe");
        createTransaction.setUserType("FS");
        createTransaction.setAvailableCredit(new BigDecimal("500.00"));
        testTransactionList.add(createTransaction);

        Transactions createTransaction2 = new Transactions();
        createTransaction2.setTransactionCode(1);
        createTransaction2.setUserName("Barrett");
        createTransaction2.setUserType("AA");
        createTransaction2.setAvailableCredit(new BigDecimal("100.00"));
        testTransactionList.add(createTransaction2);

        Transactions addCreditTransaction = new Transactions();
        addCreditTransaction.setTransactionCode(6);
        addCreditTransaction.setUserName("Joe");
        addCreditTransaction.setUserType("FS");
        addCreditTransaction.setAvailableCredit(new BigDecimal("25.50"));
        testTransactionList.add(addCreditTransaction);

        Transactions addCreditTransaction2 = new Transactions();
        addCreditTransaction2.setTransactionCode(6);
        addCreditTransaction2.setUserName("Barrett");
        addCreditTransaction2.setUserType("AA");
        addCreditTransaction2.setAvailableCredit(new BigDecimal("24.50"));
        testTransactionList.add(addCreditTransaction2);

        Transactions refundTransaction = new Transactions();
        refundTransaction.setTransactionCode(5);
        refundTransaction.setBuyerName("Barrett");
        refundTransaction.setSellerName("Joe");
        refundTransaction.setRefundCredit(new BigDecimal("150"));
        testTransactionList.add(refundTransaction);

        Transactions deleteTransaction = new Transactions();
        deleteTransaction.setTransactionCode(2);
        deleteTransaction.setBuyerName("Barrett");
        deleteTransaction.setUserType("AA");
        testTransactionList.add(deleteTransaction);

        File testTransactionFile = new File(localDir + "\\" + filePrefix + "daily_transaction_file.txt");
        FileWriter testTransactionFW = new FileWriter(testTransactionFile);
        for(Transactions transaction: testTransactionList)
            testTransactionFW.write(transaction.toString());
        testTransactionFW.close();

        file.updateUserList(filePrefix);

        File testUserFile = new File(localDir + "\\" + filePrefix + "current_user_accounts_file.txt");

        int userCount = 0;
        Scanner in = new Scanner(testUserFile);
        while(in.hasNextLine())
            userCount++;

        // Check enable/disable, possibly check credit values
        assertEquals(userCount, 1);
    }

    /**
     * Tests the updateAvailableItemsList() method in Files by creating a test set of item-creating transactions to verify.
     */
    @Test
    public void updateAvailableItemsList() throws IOException {
        List<Transactions> testTransactionList = new ArrayList<>();

        Transactions createTransaction = new Transactions();
        createTransaction.setTransactionCode(1);
        createTransaction.setUserName("Joe");
        createTransaction.setUserType("FS");
        createTransaction.setAvailableCredit(new BigDecimal("500.00"));
        testTransactionList.add(createTransaction);

        Transactions createTransaction2 = new Transactions();
        createTransaction2.setTransactionCode(1);
        createTransaction2.setUserName("Barrett");
        createTransaction2.setUserType("AA");
        createTransaction2.setAvailableCredit(new BigDecimal("100.00"));
        testTransactionList.add(createTransaction2);

        Transactions advertiseTransaction = new Transactions();
        advertiseTransaction.setTransactionCode(3);
        advertiseTransaction.setItemName("Bread");
        advertiseTransaction.setSellerName("Joe");
        advertiseTransaction.setDaysToAuction(5);
        advertiseTransaction.setMinBid(new BigDecimal("149.99"));
        testTransactionList.add(advertiseTransaction);

        Transactions bidTransaction = new Transactions();
        bidTransaction.setTransactionCode(4);
        bidTransaction.setItemName("Bread");
        bidTransaction.setSellerName("Joe");
        bidTransaction.setBuyerName("Barrett");
        bidTransaction.setNewBid(new BigDecimal("150"));
        testTransactionList.add(bidTransaction);

        File testTransactionFile = new File(localDir + "\\" + filePrefix + "daily_transaction_file.txt");
        FileWriter testTransactionFW = new FileWriter(testTransactionFile);
        for(Transactions transaction: testTransactionList)
            testTransactionFW.write(transaction.toString());
        testTransactionFW.close();

        // Refund seems like it isn't needed

        file.updateAvailableItemsList(filePrefix);

        File testItemsFile = new File(localDir + "\\" + filePrefix + "items.if.txt");

        int itemCount = 0;
        Scanner in = new Scanner(testItemsFile);
        while(in.hasNextLine())
            itemCount++;

        // Check enable/disable, possibly check credit values
        assertEquals(itemCount, 1);
    }

    @Test
    public void create() throws IOException {
        List<UserAccounts> users = new ArrayList<>();
        file.create(transaction, users);
        assertEquals(1, users.size());
    }

    @Test
    public void delete() throws IOException {
        List<UserAccounts> users = new ArrayList<>();
        UserAccounts user = new UserAccounts();
        user.setUserName("paul");
        user.setUserType("FS");
        user.setAvailableCredit(new BigDecimal("100.00"));
        user.setPassword("password");
        users.add(user);
        file.delete(transaction, users);
        assertEquals(0, users.size());
    }

    @Test
    public void advertise() {
    }

    @Test
    public void bid() {
    }

    @Test
    public void refund() {
    }

    @Test
    public void addCredit() {
    }
}