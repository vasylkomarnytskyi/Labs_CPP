package com.example.bankingSystem;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.bankingSystem.BankingSystem.*;



public class ApplicationController {
    private ThreadManager manager;
    @FXML
    private TextField textFieldNumberOfThreads;
    @FXML
    private TextField textFieldTotalCash;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnResume;
    @FXML
    private Button btnSuspend;
    @FXML
    private Button btnKill;
    @FXML
    private TextArea textAreaLog;
    @FXML
    private TableView<ThreadInfo> tableThreadsView;
    @FXML
    private TableColumn<ThreadInfo, String> ThreadsNameColumn;
    @FXML
    private TableColumn<ThreadInfo, String> ThreadsPriorityColumn;
    @FXML
    private TableColumn<ThreadInfo, String> ThreadsStatusColumn;
    @FXML
    private TableColumn<ThreadInfo, String> ThreadsChangeTimeColumn;

    @FXML
    public void Start() {
        int numberOfThreads = Integer.parseInt(textFieldNumberOfThreads.getText());
        int totalCash = Integer.parseInt(textFieldTotalCash.getText());
        Bank bank = new Bank(totalCash);
        bank.setLog(textAreaLog);

        if (manager != null) {
            for (Thread thread : manager.getThreads()) {
                if (!thread.isInterrupted()) {
                    thread.interrupt();
                }
            }
            textAreaLog.clear();
        }

        manager = new ThreadManager(numberOfThreads, bank, tableThreadsView);
    }

    @FXML
    public void KillThreads() throws InterruptedException {
        if (manager != null) {
            manager.killSelectedThread();
        }
    }

    @FXML
    public void SuspendThreads() throws InterruptedException {
        if (manager != null) {
            manager.suspendSelectedThread();
        }
    }

    @FXML
    public void ResumeThreads() {
        if (manager != null) {
            manager.resumeSelectedThread();
        }
    }

    @FXML
    public void initialize() {
        ThreadsNameColumn.setCellValueFactory(cellData -> cellData.getValue().threadNameProperty());
        ThreadsPriorityColumn.setCellValueFactory(cellData -> cellData.getValue().threadPriorityProperty().asString());
        ThreadsStatusColumn.setCellValueFactory(cellData -> cellData.getValue().threadStatusProperty());
        ThreadsChangeTimeColumn.setCellValueFactory(cellData -> cellData.getValue().changeTimeStatusProperty());
    }
}