package com.example.bankingSystem.BankingSystem;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private final Lock lock;
    private final AtomicInteger cash = new AtomicInteger();

    @FXML
    TextArea textAreaLog;

    public Bank(int cash) {
        this.cash.set(cash);
        this.lock = new ReentrantLock(true);
    }

    public int getCash() {
        return cash.intValue();
    }

    public void setCash(int cash) {
        this.cash.set(cash);
    }

    public void setLog(TextArea log) {
        this.textAreaLog = log;
    }

    public void depositCash(Client client, int amount) throws InterruptedException {
        try {
            lock.lock();
            handleSuccessfulTransaction(client, amount, false);
        } finally {
            lock.unlock();
        }
    }

    public void withdrawCash(Client client, int amount) throws InterruptedException {
        try {
            lock.lock();

            if (this.cash.intValue() < amount) {
                handleInsufficientFunds(client);
            } else {
                handleSuccessfulTransaction(client, amount, true);
            }
        } finally {
            lock.unlock();
        }
    }

    private void handleInsufficientFunds(Client client) throws InterruptedException {
        String result = "Withdrawal failed for client " + client.getClientId() +
                ": Not enough funds. Bank balance: " + this.cash;
        this.cash.addAndGet((int) (this.cash.get() * 1.01));
        updateLog(result);
    }

    private void handleSuccessfulTransaction(Client client, int amount, boolean isGiveMoney) throws InterruptedException {
        if (isGiveMoney) {
            client.addCash(amount);
            this.cash.addAndGet(-amount);
            String result = "Withdrawal client: " + client.getClientId() + " " + amount +
                    "$. Bank balance: " + this.cash;
            updateLog(result);
        } else {
            this.cash.addAndGet(amount);
            client.subtractCash(amount);
            String result = "Deposit: " + amount + "$ for Client " + client.getClientId() +
                    ". Bank balance: " + this.cash;
            updateLog(result);
        }
    }
    public synchronized void updateLog(String text) throws InterruptedException {
        Platform.runLater(() -> textAreaLog.appendText(text + "\n"));
    }
}
