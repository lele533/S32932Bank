package com.pjwstk.edu.pl.S32932Bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankServiceMockTest {
    @Mock
    ClientStorage clientStorage;

    @InjectMocks
    BankService bankService;

    @Test
    void transfer_declined_whenClientNotRegistered() {

        when(clientStorage.getClients()).thenReturn(new ArrayList<>());

        TransactionResult result = bankService.transfer("1", 100);

        assertEquals(TransactionStatus.DECLINED, result.getStatus());
        assertEquals(0, result.getNewBalance());
    }

}