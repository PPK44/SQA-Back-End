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
    Transactions advertiseTransaction = new Transactions();
    Transactions bidTransaction = new Transactions();
    Transactions refundTransaction = new Transactions();
    Transactions addCreditTransaction = new Transactions();
    Transactions enableTransaction = new Transactions();
    Transactions disableTransaction = new Transactions();

    @Before
    public void init(){
        List<Transactions> transactions = new ArrayList<>();
        transaction.setTransactionCode(1);
        transaction.setUserName("paul");
        transaction.setUserType("FS");
        transaction.setAvailableCredit(new BigDecimal("100.00"));
        transactions.add(transaction);

        enableTransaction.setTransactionCode(7);
        enableTransaction.setUserName("jesus");
        enableTransaction.setUserType("FS");
        enableTransaction.setAvailableCredit(new BigDecimal("100.00"));
        transactions.add(enableTransaction);
        disableTransaction.setTransactionCode(8);
        disableTransaction.setUserName("joe");
        disableTransaction.setUserType("FS");
        disableTransaction.setAvailableCredit(new BigDecimal("100.00"));
        transactions.add(disableTransaction);

        // Advertise Transaction Testing Data Prep
        List<Transactions> advertiseTransactions = new ArrayList<>();
        advertiseTransaction.setTransactionCode(3);
        advertiseTransaction.setItemName("GajanItemTest");
        advertiseTransaction.setSellerName("Gajan");
        advertiseTransaction.setCurrentWinningBidder("Gajan");
        advertiseTransaction.setHighestBid(new BigDecimal("999.99"));
        advertiseTransactions.add(advertiseTransaction);

        // Bid Transaction Testing Data Prep
        List<Transactions> bidTransactions = new ArrayList<>();
        bidTransaction.setTransactionCode(4);
        bidTransaction.setItemName("GreenApple");
        bidTransaction.setSellerName("GreenSeller");
        bidTransaction.setBuyerName("GreenBuyer");
        bidTransaction.setCurrentWinningBidder("GreenBuyer");
        bidTransaction.setHighestBid(new BigDecimal("90.00"));
        bidTransactions.add(bidTransaction);

        // Refund Transaction Testing Data Prep
        List<Transactions> refundTransactions = new ArrayList<>();
        refundTransaction.setTransactionCode(5);
        refundTransaction.setBuyerName("RefundBuyer");
        refundTransaction.setSellerName("RefundSeller");
        refundTransaction.setRefundCredit(new BigDecimal("1.00"));
        refundTransactions.add(refundTransaction);

        // Add Credit Transaction Testing Data Prep
        List<Transactions> addCreditTransactions = new ArrayList<>();
        addCreditTransaction.setUserName("AddCreditUser");
        addCreditTransaction.setAvailableCredit(new BigDecimal("10.00"));
        addCreditTransactions.add(addCreditTransaction);
    }

    /**
     * Tests the updateUserList() method in Files by creating a test set of user-creating transactions to verify.
     */
//    @Test
//    public void updateUserList() throws IOException {
//        List<Transactions> testTransactionList = new ArrayList<>();
//
//        Transactions createTransaction = new Transactions();
//        createTransaction.setTransactionCode(1);
//        createTransaction.setUserName("Joe");
//        createTransaction.setUserType("FS");
//        createTransaction.setAvailableCredit(new BigDecimal("500.00"));
//        testTransactionList.add(createTransaction);
//
//        Transactions createTransaction2 = new Transactions();
//        createTransaction2.setTransactionCode(1);
//        createTransaction2.setUserName("Barrett");
//        createTransaction2.setUserType("AA");
//        createTransaction2.setAvailableCredit(new BigDecimal("100.00"));
//        testTransactionList.add(createTransaction2);
//
//        Transactions addCreditTransaction = new Transactions();
//        addCreditTransaction.setTransactionCode(6);
//        addCreditTransaction.setUserName("Joe");
//        addCreditTransaction.setUserType("FS");
//        addCreditTransaction.setAvailableCredit(new BigDecimal("25.50"));
//        testTransactionList.add(addCreditTransaction);
//
//        Transactions addCreditTransaction2 = new Transactions();
//        addCreditTransaction2.setTransactionCode(6);
//        addCreditTransaction2.setUserName("Barrett");
//        addCreditTransaction2.setUserType("AA");
//        addCreditTransaction2.setAvailableCredit(new BigDecimal("24.50"));
//        testTransactionList.add(addCreditTransaction2);
//
//        Transactions refundTransaction = new Transactions();
//        refundTransaction.setTransactionCode(5);
//        refundTransaction.setBuyerName("Barrett");
//        refundTransaction.setSellerName("Joe");
//        refundTransaction.setRefundCredit(new BigDecimal("150"));
//        testTransactionList.add(refundTransaction);
//
//        Transactions deleteTransaction = new Transactions();
//        deleteTransaction.setTransactionCode(2);
//        deleteTransaction.setBuyerName("Barrett");
//        deleteTransaction.setUserType("AA");
//        testTransactionList.add(deleteTransaction);
//
//        file.updateTransactionList(testTransactionList);
//
//        file.updateUserList();
//
//        List<UserAccounts> users = file.getUserList();
//
//        assertEquals(users.size(), 1);
//    }

    /**
     * Tests the updateAvailableItemsList() method in Files by creating a test set of item-creating transactions to verify.
     */
//    @Test
//    public void updateAvailableItemsList() throws IOException {
//        List<Transactions> testTransactionList = new ArrayList<>();
//
//        Transactions createTransaction = new Transactions();
//        createTransaction.setTransactionCode(1);
//        createTransaction.setUserName("Joe");
//        createTransaction.setUserType("FS");
//        createTransaction.setAvailableCredit(new BigDecimal("500.00"));
//        testTransactionList.add(createTransaction);
//
//        Transactions createTransaction2 = new Transactions();
//        createTransaction2.setTransactionCode(1);
//        createTransaction2.setUserName("Barrett");
//        createTransaction2.setUserType("AA");
//        createTransaction2.setAvailableCredit(new BigDecimal("100.00"));
//        testTransactionList.add(createTransaction2);
//
//        Transactions advertiseTransaction = new Transactions();
//        advertiseTransaction.setTransactionCode(3);
//        advertiseTransaction.setItemName("Bread");
//        advertiseTransaction.setSellerName("Joe");
//        advertiseTransaction.setDaysToAuction(5);
//        advertiseTransaction.setMinBid(new BigDecimal("149.99"));
//        testTransactionList.add(advertiseTransaction);
//
//        Transactions bidTransaction = new Transactions();
//        bidTransaction.setTransactionCode(4);
//        bidTransaction.setItemName("Bread");
//        bidTransaction.setSellerName("Joe");
//        bidTransaction.setBuyerName("Barrett");
//        bidTransaction.setNewBid(new BigDecimal("150"));
//        testTransactionList.add(bidTransaction);
//
//        file.updateTransactionList(testTransactionList);
//
//        // Refund seems like it isn't needed
//
//        file.updateAvailableItemsList();
//
//        List<AvailableItems> items = file.getAvailableItemsList();
//
//        // Check enable/disable, possibly check credit values
//        assertEquals(items.size(), 1);
//    }

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
    public void advertise() throws IOException {
        List<AvailableItems> items = new ArrayList<>();
        file.advertise(advertiseTransaction, items);
        assertEquals(1, items.size());

    }

    @Test
    public void bid() throws IOException {
        List<AvailableItems> items = new ArrayList<>();
        AvailableItems bidItem = new AvailableItems();
        bidItem.setItemName("GreenApple");
        bidItem.setSellerName("GreenSeller");
        bidItem.setCurrentWinningBidder("PoorBuyer");
        bidItem.setHighestBid(new BigDecimal("55.00"));
        bidItem.setNumberOfDaysLeft(5);
        items.add(bidItem);
        file.bid(bidTransaction, items);
        assertEquals("GreenBuyer", items.get(0).getCurrentWinningBidder());
        assertEquals(new BigDecimal("90.00"), items.get(0).getHighestBid());
    }

    @Test
    public void refund() throws IOException {
        List<UserAccounts> users = new ArrayList<>();
        UserAccounts refundBuyerUser = new UserAccounts();
        UserAccounts refundSellerUser = new UserAccounts();

        // Generate Refund Buyer Test Data
        refundBuyerUser.setUserName("RefundBuyer");
        refundBuyerUser.setUserType("FS");
        refundBuyerUser.setAvailableCredit(new BigDecimal("20.00"));
        refundBuyerUser.setPassword("password");
        users.add(refundBuyerUser);

        // Generate Refund Seller Test Data
        refundSellerUser.setUserName("RefundSeller");
        refundSellerUser.setUserType("FS");
        refundSellerUser.setAvailableCredit(new BigDecimal("20.00"));
        refundSellerUser.setPassword("password");
        users.add(refundSellerUser);

        // Start the Test
        file.refund(refundTransaction, users);
        // Test Results
        assertEquals(new BigDecimal("21.00"),users.get(0).getAvailableCredit());
        assertEquals(new BigDecimal("19.00"), users.get(1).getAvailableCredit());

    }

    @Test
    public void addCredit() throws IOException {
        List<UserAccounts> users = new ArrayList<>();
        UserAccounts addCreditUser = new UserAccounts();

        // Generate AddCredit User
        addCreditUser.setUserName("AddCreditUser");
        addCreditUser.setUserType("FS");
        addCreditUser.setAvailableCredit(new BigDecimal("10.00"));
        addCreditUser.setPassword("password");
        users.add(addCreditUser);

        // Start the Test
        file.addCredit(addCreditTransaction, users);
        // Test Results
        assertEquals(new BigDecimal("20.00"), users.get(0).getAvailableCredit());


    }

    @Test
    public void enable() throws IOException {

        List<UserAccounts> users = new ArrayList<>();
        UserAccounts enableUser = new UserAccounts();

        // Generate Enable User
        enableUser.setUserName(enableTransaction.getUserName());
        enableUser.setUserType("DS");
        enableUser.setAvailableCredit(enableTransaction.getAvailableCredit());
        enableUser.setPassword("password");
        users.add(enableUser);

        // Start the Test
        file.enable(enableTransaction, users);
        // Test Results
        assertEquals("FS", users.get(0).getUserType());

    }

    @Test
    public void disable() throws IOException {

        List<UserAccounts> users = new ArrayList<>();
        UserAccounts disableUser = new UserAccounts();

        // Generate Enable User
        disableUser.setUserName(disableTransaction.getUserName());
        disableUser.setUserType(disableTransaction.getUserType());
        disableUser.setAvailableCredit(disableTransaction.getAvailableCredit());
        disableUser.setPassword("password");
        users.add(disableUser);

        // Start the Test
        file.disable(disableTransaction, users);
        // Test Results
        assertEquals("DS", users.get(0).getUserType());

    }

    @Test
    public void decrementAuctionDay(){

    }

    @Test
    public void completeAuction(){

    }
}