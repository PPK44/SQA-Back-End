package com.BackEnd.Tests;

import com.BackEnd.AvailableItems;
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

    @Test
    public void updateTransactionList() throws IOException {
        //Not needed
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

        file.updateTransactionList(testTransactionList);

        file.updateUserList();

        List<UserAccounts> users = file.getUserList();

        assertEquals(users.size(), 1);
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

        file.updateTransactionList(testTransactionList);

        // Refund seems like it isn't needed

        file.updateAvailableItemsList();

        List<AvailableItems> items = file.getAvailableItemsList();

        // Check enable/disable, possibly check credit values
        assertEquals(items.size(), 1);
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