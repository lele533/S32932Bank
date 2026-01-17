package com.pjwstk.edu.pl.S32932Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankServiceIT {

    @Autowired
    BankService bankService;

    @Autowired
    ClientStorage clientStorage;

    @BeforeEach
    void reset() {

        clientStorage.setClients(new ArrayList<>());
    }


    @Test
    void registerClient_andGetClient_integrationTest() {
        bankService.registerClient("Adam", "Nowak", "1", 1000);

        Client client = bankService.getClient("1");

        assertEquals("Adam", client.getName());
        assertEquals("Nowak", client.getSurname());
        assertEquals(1000, client.getMoneyOnAccount());
    }

    @Test
    void transfer_integrationTest() {
        bankService.registerClient("Adam", "Nowak", "1", 1000);

        TransactionResult result = bankService.transfer("1", 300);

        assertEquals(TransactionStatus.ACCEPTED, result.getStatus());
        assertEquals(700, result.getNewBalance());
        assertEquals(700, bankService.getClient("1").getMoneyOnAccount());
    }

    @Test
    void deposit_integrationTest() {
        bankService.registerClient("Adam", "Nowak", "1", 500);

        TransactionResult result = bankService.deposit("1", 200);

        assertEquals(TransactionStatus.ACCEPTED, result.getStatus());
        assertEquals(700, result.getNewBalance());
        assertEquals(700, bankService.getClient("1").getMoneyOnAccount());
    }


}
