package com.example.bankingSystem.BankingSystem;

import javafx.application.Platform;
import javafx.scene.control.TableView;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Client implements Runnable {

    private final int clientId;
    private int cash;
    private final Bank bank;
    private final Random rand = new Random();
    private final Object pauseLock = new Object();
    private volatile boolean paused = false;
    TableView<ThreadInfo> table;
    ThreadManager manager;
    public Client(Bank bank, int clientId, ThreadManager manager, TableView<ThreadInfo> table) {
        this.bank = bank;
        this.clientId = clientId;
        this.manager = manager;
        this.table = table;
        cash = rand.nextInt(10000);
    }

    public int getClientId() {
        return clientId;
    }

    public int getCash() {
        return cash;
    }

    public void addCash(int cash) {
        this.cash += cash;
    }

    public void subtractCash(int cash) {
        this.cash -= cash;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (pauseLock) {
                    while (paused) {
                        pauseLock.wait();
                    }

                    if (Thread.interrupted()) {
                        break;
                    }

                    performTransaction();
                    updateStatusChangeTime();
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private boolean paused() {
        return paused;
    }

    private void performTransaction() {
        if (getCash() < 1000) {
            try {
                bank.withdrawCash(this, rand.nextInt(1000, 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                bank.depositCash(this, rand.nextInt(cash));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateStatusChangeTime() {
        manager.threadInfoList.stream()
                .filter(threadInfo -> threadInfo.getThreadName().equals(Thread.currentThread().getName()))
                .findFirst()
                .ifPresent(threadInfo -> {
                    threadInfo.setChangeTimeStatus(new Date().toString());
                    Platform.runLater(manager.tableThreadsView::refresh);
                });
    }

    public void pause() {
        synchronized (pauseLock) {
            paused = true;
            System.out.println("Thread " + Thread.currentThread().getName() + " paused");
        }
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
            System.out.println("Thread " + Thread.currentThread().getName() + " resumed");
        }
    }
}

