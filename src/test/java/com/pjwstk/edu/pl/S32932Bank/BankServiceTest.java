package com.pjwstk.edu.pl.S32932Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    private ClientStorage clientStorage;
    private BankService bankService;

    @BeforeEach
    void setUp() {
        clientStorage = new ClientStorage();
        clientStorage.setClients(new ArrayList<>());
        bankService = new BankService(clientStorage);
    }

    @Test
    void registerClient_addsClientToStorage() {
        bankService.registerClient("Adam", "Nowak", "10", 500);

        assertEquals(1, clientStorage.getClients().size());
        assertEquals("10", clientStorage.getClients().get(0).getClientID());
        assertEquals(500, clientStorage.getClients().get(0).getMoneyOnAccount());
    }

    @Test
    void registerClient_throws_whenClientAlreadyExists() {
        bankService.registerClient("Adam", "Nowak", "10", 500);

        assertThrows(RuntimeException.class,
                () -> bankService.registerClient("Jan", "Kowalski", "10", 100));
    }

    @Test
    void transfer_declined_whenClientNotRegistered() {
        TransactionResult result = bankService.transfer("999", 100);

        assertEquals(TransactionStatus.DECLINED, result.getStatus());
        assertEquals(0, result.getNewBalance());
    }

    @Test
    void transfer_declined_whenInsufficientFunds() {
        bankService.registerClient("Adam", "Nowak", "10", 50);

        TransactionResult result = bankService.transfer("10", 100);

        assertEquals(TransactionStatus.DECLINED, result.getStatus());
        assertEquals(50, result.getNewBalance());
    }

    @Test
    void transfer_accepted_decreasesBalance() {
        bankService.registerClient("Adam", "Nowak", "10", 500);

        TransactionResult result = bankService.transfer("10", 200);

        assertEquals(TransactionStatus.ACCEPTED, result.getStatus());
        assertEquals(300, result.getNewBalance());
        assertEquals(300, bankService.getClient("10").getMoneyOnAccount());
    }

    @Test
    void deposit_declined_whenClientNotRegistered() {
        TransactionResult result = bankService.deposit("999", 100);

        assertEquals(TransactionStatus.DECLINED, result.getStatus());
        assertEquals(0, result.getNewBalance());
    }


    @Test
    void getClient_throws_whenNotRegistered() {
        assertThrows(RuntimeException.class, () -> bankService.getClient("999"));
    }
}
