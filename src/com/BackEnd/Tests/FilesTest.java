package com.BackEnd.Tests;

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