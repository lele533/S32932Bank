package com.pjwstk.edu.pl.S32932Bank;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientStorage {

    private List<Client> clients = new ArrayList<>();

    public ClientStorage() {
        clients.add(new Client("Jan", "Kowalski", "1", 1000));
        clients.add(new Client("Jan", "Barlos", "2", 2000));
        clients.add(new Client("Jan", "Kowalski", "3", 3000));
        clients.add(new Client("Jan", "Kowalski", "4", 4000));

    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
