package com.BackEnd;
import java.io.*;
import java.util.List;

import com.BackEnd.Files;

/**
 * This backend project updates account and item data for an auction system on a nightly basis.
 * It accesses and manipulates a user accounts file, an available items file, and a daily transaction file.
 */
public class Main extends FileIO {

    public static void main(String[] args) throws IOException {

        // Files instance for storing accounts/items/transactions files
        Files run = new Files();

        try{
            run.updateTransactionList();
            run.updateAvailableItemsList();
            run.updateUserList();
        } finally {
            // Placeholder
        }

    }

}
