package com.pjwstk.edu.pl.S32932Bank;

public class Client {
    private String name;
    private String surname;
    private String ClientID;
    private int moneyOnAccount;

    public Client(String name, String surname, String ClientID, int moneyOnAccount) {
        this.name = name;
        this.surname = surname;
        this.ClientID = ClientID;
        this.moneyOnAccount = moneyOnAccount;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public int getMoneyOnAccount() {
        return moneyOnAccount;
    }

    public void setMoneyOnAccount(int moneyOnAccount) {
        this.moneyOnAccount = moneyOnAccount;
    }

    @Override
    public String toString() {
        return "Client{" + "name=" + name + ", surname=" + surname + ", ClientID=" + ClientID + ", moneyOnAccount=" + moneyOnAccount + '}';
    }
}
