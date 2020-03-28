package com.BackEnd.Tests;

import com.BackEnd.AvailableItems;
import com.BackEnd.Files;
import com.BackEnd.Transactions;

import com.BackEnd.UserAccounts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Use junit4
 */
public class FilesTest {

    Files file = new Files();

    Transactions transaction = new Transactions();
    Transactions advertiseTransaction = new Transactions();
    Transactions bidTransaction = new Transactions();
    Transactions refundTransaction = new Transactions();
    Transactions addCreditTransaction = new Transactions();
    Transactions enableTransaction = new Transactions();
    Transactions disableTransaction = new Transactions();
    List<Transactions> transactions = new ArrayList<>();

    @Before
    public void init(){

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
        advertiseTransaction.setTransactionCode(3);
        advertiseTransaction.setItemName("GajanItemTest");
        advertiseTransaction.setSellerName("Gajan");
        advertiseTransaction.setCurrentWinningBidder("Gajan");
        advertiseTransaction.setHighestBid(new BigDecimal("999.99"));

        // Bid Transaction Testing Data Prep
        bidTransaction.setTransactionCode(4);
        bidTransaction.setItemName("GreenApple");
        bidTransaction.setSellerName("GreenSeller");
        bidTransaction.setBuyerName("GreenBuyer");
        bidTransaction.setCurrentWinningBidder("GreenBuyer");
        bidTransaction.setHighestBid(new BigDecimal("90.00"));

        // Refund Transaction Testing Data Prep
        refundTransaction.setTransactionCode(5);
        refundTransaction.setBuyerName("RefundBuyer");
        refundTransaction.setSellerName("RefundSeller");
        refundTransaction.setRefundCredit(new BigDecimal("1.00"));

        // Add Credit Transaction Testing Data Prep
        addCreditTransaction.setUserName("AddCreditUser");
        addCreditTransaction.setAvailableCredit(new BigDecimal("10.00"));

        // Update Method Testing Data Prep
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
        testTransactionList.add(enableTransaction); //transaction code 7
        testTransactionList.add(disableTransaction);//transaction code 8

        List<UserAccounts> users = new ArrayList<>();
        file.updateUserList(users, testTransactionList);
        assertEquals(2, users.size());
    }

    /**
     * Tests the updateAvailableItemsList() loop by never entering loop
     */
    @Test
    public void neverEnterLoop() throws IOException {
        List<Transactions> testTransactionList = new ArrayList<>();
        List<AvailableItems> items = new ArrayList<>();

        file.updateAvailableItemsList(items, testTransactionList);
        assertTrue(items.isEmpty());

    }

    /**
     * Tests the updateAvailableItemsList() loop by entering loop once
     */
    @Test
    public void enterLoopOnce() throws IOException {
        List<Transactions> testTransactionList = new ArrayList<>();
        List<AvailableItems> items = new ArrayList<>();
        testTransactionList.add(bidTransaction);
        testTransactionList.add(advertiseTransaction);

        file.updateAvailableItemsList(items, testTransactionList);
        assertEquals(2, testTransactionList.size());

    }
    /**
     * Tests the updateAvailableItemsList() loop by entering loop twice
     */
    @Test
    public void enterLoopTwice() throws IOException {
        List<Transactions> testTransactionList = new ArrayList<>();
        List<AvailableItems> items = new ArrayList<>();
        testTransactionList.add(bidTransaction);
        testTransactionList.add(advertiseTransaction);

        file.updateAvailableItemsList(items, testTransactionList);
        assertEquals(2, testTransactionList.size());

    }

    /**
     * Tests the updateAvailableItemsList() method in Files by creating a test set of item-creating transactions to verify.
     */
    @Test
    public void updateAvailableItemsList() throws IOException {
        List<Transactions> testTransactionList = new ArrayList<>();
        List<AvailableItems> items = new ArrayList<>();

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


        file.updateAvailableItemsList(items, testTransactionList);
        assertEquals(1, items.size());
    }

    /**
     * Decision Coverage
     */
    @Test
    public void create()  {
        List<UserAccounts> users = new ArrayList<>();
        file.create(transaction, users);
        assertEquals(1, users.size());
    }

    /**
     * Decision Coverage
     */
    @Test
    public void failCreate() {
        List<UserAccounts> users = new ArrayList<>();
        UserAccounts user = new UserAccounts();
        user.setUserName(transaction.getUserName());
        users.add(user);
        file.create(transaction, users);
        assertEquals(1, users.size());
    }

    @Test
    public void delete() {
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
    public void advertise(){
        List<AvailableItems> items = new ArrayList<>();
        file.advertise(advertiseTransaction, items);
        assertEquals(1, items.size());

    }

    @Test
    public void bid(){
        List<AvailableItems> items = new ArrayList<>();
        AvailableItems bidItem = new AvailableItems();
        bidItem.setItemName("GreenApple");
        bidItem.setSellerName("GreenSeller");
        bidItem.setCurrentWinningBidder("PoorBuyer");
        bidItem.setHighestBid(new BigDecimal("55.00"));
        //bidItem.setNumberOfDaysLeft(5);
        items.add(bidItem);
        file.bid(bidTransaction, items);
        assertEquals("PoorBuyer", items.get(0).getCurrentWinningBidder());
        assertEquals(new BigDecimal("55.00"), items.get(0).getHighestBid());
    }

    @Test
    public void refund(){
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
    public void addCredit(){
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
    public void enable(){

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
    public void disable(){

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
    public void decrementAuctionDay() throws IOException {
        // Decrement Auction Day
        List<AvailableItems> decrementAuctionItems = new ArrayList<>();
        AvailableItems decrementAuctionitem = new AvailableItems();

        // Create Items to decrement
        decrementAuctionitem.setItemName("DecrementItem");
        decrementAuctionitem.setHighestBid(new BigDecimal("10.00"));
        decrementAuctionitem.setCurrentWinningBidder("DIBuyer");
        decrementAuctionitem.setSellerName("DISeller");
        decrementAuctionitem.setNumberOfDaysLeft(4);
        decrementAuctionItems.add(decrementAuctionitem);

        // Create Users to Handle the item
        List<UserAccounts> decrementUsers = new ArrayList<>();
        UserAccounts decrementBuyer = new UserAccounts();
        UserAccounts decrementSeller = new UserAccounts();

        // Create Seller
        decrementSeller.setAvailableCredit(new BigDecimal("50.00"));
        decrementSeller.setPassword("passwords");
        decrementSeller.setUserName("DISeller");
        decrementSeller.setUserType("FS");
        decrementUsers.add(decrementSeller);

        // Create Buyer
        decrementBuyer.setUserType("FS");
        decrementBuyer.setUserName("DIBuyer");
        decrementBuyer.setAvailableCredit(new BigDecimal("20.00"));
        decrementBuyer.setPassword("passwords");
        decrementUsers.add(decrementBuyer);

        // Run test
        file.decrementAuctionDay(decrementAuctionItems, decrementUsers);

        //Test Results
        assertEquals(3, decrementAuctionItems.get(0).getNumberOfDaysLeft());


    }

    @Test
    public void decrementAuctionDayCallCompleteAuction(){

    }

    @Test
    public void completeAuction(){

        // Complete Auction
        List<AvailableItems> completeAuctionItems = new ArrayList<>();
        AvailableItems completeAuctionitem = new AvailableItems();

        // Create Items to complete
        completeAuctionitem.setItemName("CompleteItem");
        completeAuctionitem.setHighestBid(new BigDecimal("10.00"));
        completeAuctionitem.setCurrentWinningBidder("DIBuyer");
        completeAuctionitem.setSellerName("DISeller");
        completeAuctionitem.setNumberOfDaysLeft(0);
        completeAuctionItems.add(completeAuctionitem);

        // Create Users to Handle the item
        List<UserAccounts> completeUsers = new ArrayList<>();
        UserAccounts completeBuyer = new UserAccounts();
        UserAccounts completeSeller = new UserAccounts();

        // Create Seller
        completeSeller.setAvailableCredit(new BigDecimal("50.00"));
        completeSeller.setPassword("passwords");
        completeSeller.setUserName("DISeller");
        completeSeller.setUserType("FS");
        completeUsers.add(completeSeller);

        // Create Buyer
        completeBuyer.setUserType("FS");
        completeBuyer.setUserName("DIBuyer");
        completeBuyer.setAvailableCredit(new BigDecimal("20.00"));
        completeBuyer.setPassword("passwords");
        completeUsers.add(completeBuyer);

        // Run test
        file.completeAuction(completeAuctionItems, completeAuctionitem, completeUsers);

        //Test Results
        // Test that seller gets money
        assertEquals(new BigDecimal("60.00"), completeUsers.get(0).getAvailableCredit());
        // Test that buy loses money
        assertEquals(new BigDecimal("10.00"), completeUsers.get(1).getAvailableCredit());
        // Test that Item is Deleted
        assertEquals(0, completeAuctionItems.size());

    }
}