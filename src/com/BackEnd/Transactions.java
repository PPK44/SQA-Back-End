package com.BackEnd;

/**
 *
 */
public class Transactions {

    /**
     *
     */
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     *
     */
    Transactions(){

    }

    Transactions(String transactionId){
        this.id = transactionId;
    }

}
