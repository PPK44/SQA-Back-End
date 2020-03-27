package com.BackEnd.Tests;

import com.BackEnd.Files;
import com.BackEnd.Transactions;
import org.junit.Before;
import org.junit.Test;

import com.BackEnd.FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileIOTest {

    // Data for all three files accessed: the available items file, the user accounts file, and the daily transaction file.
//    String localDir = System.getProperty("user.dir");
//    File availableItemsFile = new File(localDir + "\\items.if.txt");
//    File userAccountsFile = new File(localDir + "\\current_user_accounts_file.txt");
//    File transactionFile = new File(localDir + "\\daily_transaction_file.txt");

    // Linux Paths
    String localDir = System.getProperty("user.dir");
    File availableItemsFile = new File(localDir + "/items.if.txt");
    File userAccountsFile = new File(localDir + "/current_user_accounts_file.txt");
    File transactionFile = new File(localDir + "/daily_transaction_file.txt");

    FileIO fileIO = new FileIO();

    Transactions createTransaction = new Transactions();



    @Before
    public void setUp() throws Exception {
        List<Transactions> testTransactions = new ArrayList<>();
        createTransaction.setTransactionCode(01);
        createTransaction.setUserName("MR.RUNESCAPEMAN");
        createTransaction.setUserType("FS");
        createTransaction.setAvailableCredit(new BigDecimal("00.22"));
        testTransactions.add(createTransaction);


    }

    @Test
    public void parseTransactions() throws FileNotFoundException {
        List<Transactions> parsedTransaction =  fileIO.parseTransactions();

        // Test if the File is being read
        assertEquals(18, parsedTransaction.size());

        // Check if Create is parsed correctly (01)
        assertEquals(testTransactions.get(0).getTransactionCode(), parsedTransaction.get(0).getTransactionCode());
        assertEquals("MR.RUNESCAPEMAN", parsedTransaction.get(0).getUserName());
        assertEquals("FS", parsedTransaction.get(0).getUserType());
        assertEquals(new BigDecimal("00.22"), parsedTransaction.get(0).getAvailableCredit());

        // Check if Delete is parted correctly (02)

        // Check if Advertise is parsed correctly (03)
        // Check if Bid is parsed correctly (04)
        // Check if Refund is parsed correctly (05)
        // Check if Add Credit is parsed correctly (06)
        // Check if End of Session is parsed correctly (00)





    }

    @Test
    public void parseItems() {
    }

    @Test
    public void parseUsers() {
    }

    @Test
    public void writeUserFile() {
    }

    @Test
    public void writeItemFile() {
    }
}