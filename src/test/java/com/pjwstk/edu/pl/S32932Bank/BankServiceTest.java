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
    void registerClientTestTrue() {
        bankService.registerClient("Adam", "Nowak", "10", 500);

        assertEquals(1, clientStorage.getClients().size());
        assertEquals("10", clientStorage.getClients().get(0).getClientID());
        assertEquals(500, clientStorage.getClients().get(0).getMoneyOnAccount());
    }

    @Test
    void registerClientWithTheSameId() {
        bankService.registerClient("Adam", "Nowak", "10", 500);

        assertThrows(RuntimeException.class,
                () -> bankService.registerClient("Jan", "Kowalski", "10", 100));
    }

    @Test
    void transferDeclinedWhenClientNotExist() {
        TransactionResult result = bankService.transfer("999", 100);

        assertEquals(TransactionStatus.DECLINED, result.getStatus());
        assertEquals(0, result.getNewBalance());
    }

    @Test
    void transferDeclinedWhenInsufficientFunds() {
        bankService.registerClient("Adam", "Nowak", "10", 50);

        TransactionResult result = bankService.transfer("10", 100);

        assertEquals(TransactionStatus.DECLINED, result.getStatus());
        assertEquals(50, result.getNewBalance());
    }

    @Test
    void transferTestWhereEverythingIsOk() {
        bankService.registerClient("Adam", "Nowak", "10", 500);

        TransactionResult result = bankService.transfer("10", 200);

        assertEquals(TransactionStatus.ACCEPTED, result.getStatus());
        assertEquals(300, result.getNewBalance());
        assertEquals(300, bankService.getClient("10").getMoneyOnAccount());
    }

    @Test
    void depositDeclinedWhenClientNotRegistered() {
        TransactionResult result = bankService.deposit("999", 100);

        assertEquals(TransactionStatus.DECLINED, result.getStatus());
        assertEquals(0, result.getNewBalance());
    }


    @Test
    void getClientThrowsWhenNotRegistered() {
        assertThrows(RuntimeException.class, () -> bankService.getClient("999"));
    }
}
