package com.example.bankingSystem.BankingSystem;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadManager {
    private final List<Thread> threads;
    private final List<Client> clients;

    @FXML
    TableView<ThreadInfo> tableThreadsView;
    ObservableList<ThreadInfo> threadInfoList;

    public ThreadManager(int numOfThreads, Bank bank, TableView<ThreadInfo> threadsTable) {
        this.tableThreadsView = threadsTable;
        this.threads = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.threadInfoList = FXCollections.observableArrayList();

        Random random = new Random();

        for (int i = 0; i < numOfThreads; i++) {
            Client newClient = new Client(bank, i + 1, this, this.tableThreadsView);
            clients.add(newClient);
            Thread thread = new Thread(newClient);
            int priority = Thread.NORM_PRIORITY;
            thread.setPriority(priority);
            thread.setName(String.valueOf(i + 1));
            threads.add(thread);

            ThreadInfo threadInfo = new ThreadInfo(
                    thread.getName(),
                    "Running",
                    thread.getPriority(),
                    new Date().toString()
            );
            threadInfoList.add(threadInfo);
        }

        this.tableThreadsView.setItems(threadInfoList);

        for (Thread thread : threads) {
            thread.start();
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void suspendSelectedThread() {
        ObservableList<ThreadInfo> selectedThreadsInfo = tableThreadsView.getSelectionModel().getSelectedItems();

        for (ThreadInfo selectedThreadInfo : selectedThreadsInfo) {
            Client selectedClient = getClientByThreadId(selectedThreadInfo.getThreadName());

            if (selectedClient != null) {
                selectedClient.pause();
                selectedThreadInfo.setThreadStatus("Paused");
            }
        }

        tableThreadsView.refresh();
    }

    public void resumeSelectedThread() {
        ObservableList<ThreadInfo> selectedThreadsInfo = tableThreadsView.getSelectionModel().getSelectedItems();

        for (ThreadInfo selectedThreadInfo : selectedThreadsInfo) {
            Client selectedClient = getClientByThreadId(selectedThreadInfo.getThreadName());

            if (selectedClient != null) {
                selectedThreadInfo.setThreadStatus("Running");
                selectedClient.resume();
            }
        }

        tableThreadsView.refresh();
    }

    public void killSelectedThread() {
        ObservableList<ThreadInfo> selectedThreadsInfo = tableThreadsView.getSelectionModel().getSelectedItems();

        for (ThreadInfo selectedThreadInfo : selectedThreadsInfo) {
            Thread selectedThread = getThreadByThreadId(selectedThreadInfo.getThreadName());

            if (selectedThread != null) {
                selectedThread.interrupt();
                selectedThreadInfo.setThreadStatus("Kill");
            }
        }

        tableThreadsView.refresh();
    }

    private Client getClientByThreadId(String threadId) {
        return clients.stream()
                .filter(client -> String.valueOf(client.getClientId()).equals(threadId))
                .findFirst()
                .orElse(null);
    }

    private Thread getThreadByThreadId(String threadId) {
        return threads.stream()
                .filter(thread -> Objects.equals(thread.getName(), threadId))
                .findFirst()
                .orElse(null);
    }
}