package com.pjwstk.edu.pl.S32932Bank;

import org.springframework.stereotype.Service;

@Service
public class BankService {

    private final ClientStorage clientStorage;

    public BankService(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }


    public Client registerClient(String name, String surname, String clientID, int initialBalance) {

        if (name == null || name.isBlank()) throw new RuntimeException("Name empty");
        if (surname == null || surname.isBlank()) throw new RuntimeException("Surname empty");
        if (clientID == null || clientID.isBlank()) throw new RuntimeException("ClientID empty");
        if (initialBalance < 0) throw new RuntimeException("Negative balance");

        for (Client c : clientStorage.getClients()) {
            if (c.getClientID().equals(clientID)) {
                throw new RuntimeException("Client already exists");
            }
        }

        Client client = new Client(name, surname, clientID, initialBalance);
        clientStorage.getClients().add(client);
        return client;
    }


    public TransactionResult transfer(String clientID, int amount) {

        Client client = null;
        for (Client c : clientStorage.getClients()) {
            if (c.getClientID().equals(clientID)) {
                client = c;
            }
        }

        if (client == null) {
            return new TransactionResult(TransactionStatus.DECLINED, 0, "Client not registered");
        }

        if (amount <= 0) {
            return new TransactionResult(TransactionStatus.DECLINED,
                    client.getMoneyOnAccount(), "Amount must be > 0");
        }

        if (client.getMoneyOnAccount() < amount) {
            return new TransactionResult(TransactionStatus.DECLINED,
                    client.getMoneyOnAccount(), "Insufficient funds");
        }

        int newBalance = client.getMoneyOnAccount() - amount;
        client.setMoneyOnAccount(newBalance);

        return new TransactionResult(TransactionStatus.ACCEPTED, newBalance, "Transfer accepted");
    }


    public TransactionResult deposit(String clientID, int amount) {

        Client client = null;
        for (Client c : clientStorage.getClients()) {
            if (c.getClientID().equals(clientID)) {
                client = c;
            }
        }

        if (client == null) {
            return new TransactionResult(TransactionStatus.DECLINED, 0, "Client not registered");
        }

        if (amount <= 0) {
            return new TransactionResult(TransactionStatus.DECLINED,
                    client.getMoneyOnAccount(), "Amount must be > 0");
        }

        int newBalance = client.getMoneyOnAccount() + amount;
        client.setMoneyOnAccount(newBalance);

        return new TransactionResult(TransactionStatus.ACCEPTED, newBalance, "Deposit accepted");
    }


    public Client getClient(String clientID) {
        for (Client client : clientStorage.getClients()) {
            if (client.getClientID().equals(clientID)) {
                return client;
            }
        }
        throw new RuntimeException("Client not registered");
    }
}
