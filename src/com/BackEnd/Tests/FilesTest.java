package com.BackEnd.Tests;

import com.BackEnd.AvailableItems;
import com.BackEnd.Files;
import com.BackEnd.Transactions;

import com.BackEnd.UserAccounts;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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


    @Before
    public void init(){
        List<Transactions> transactions = new ArrayList<>();
        transaction.setTransactionCode(1);
        transaction.setUserName("paul");
        transaction.setUserType("FS");
        transaction.setAvailableCredit(new BigDecimal("100.00"));
        transactions.add(transaction);

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



    @Test
    public void updateTransactionList() throws FileNotFoundException {
        file.updateTransactionList();

    }

    /**
     * for this you need to create a transaction with each transaction code to cover
     * each statement in the switch, I have done one with transaction code = 1
     * @throws IOException
     */
    @Test
    public void updateUserList() throws IOException {
        file.updateUserList();
    }

    /**
     * for this you need to create a transaction with each transaction code to cover
     * each statement in the switch, I have done one with transaction code = 1
     * @throws IOException
     */
    @Test
    public void updateAvailableItemsList() throws IOException {
        file.updateAvailableItemsList();
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
}