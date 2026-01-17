package com.pjwstk.edu.pl.S32932Bank;

public class TransactionResult {
    private final TransactionStatus status;
    private final int newBalance;
    private final String message;

    public TransactionResult(TransactionStatus status, int newBalance, String message) {
        this.status = status;
        this.newBalance = newBalance;
        this.message = message;
    }

    public TransactionStatus getStatus() { return status; }
    public int getNewBalance() { return newBalance; }
    public String getMessage() { return message; }
}
