package com.BackEnd;
import java.io.*;
import java.util.List;

import com.BackEnd.Files;

/**
 *
 */
public class Main extends FileIO {


    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{

        Files run = new Files();

	    try{
	        run.updateTransactionList();
            run.updateAvailableItemsList();
            run.updateUserList();

        }finally{

        }

    }




}
