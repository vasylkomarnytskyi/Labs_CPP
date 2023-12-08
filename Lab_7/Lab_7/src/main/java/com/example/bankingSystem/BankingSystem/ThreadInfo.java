package com.example.bankingSystem.BankingSystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ThreadInfo {
    private final SimpleStringProperty threadName;
    private final SimpleStringProperty threadStatus;
    private final SimpleIntegerProperty threadPriority;
    private final SimpleStringProperty changeTimeStatus;

    public ThreadInfo(String name, String status, int priority, String time) {
        this.threadName = new SimpleStringProperty(name);
        this.threadStatus = new SimpleStringProperty(status);
        this.threadPriority = new SimpleIntegerProperty(priority);
        this.changeTimeStatus = new SimpleStringProperty(time);
    }

    public String getThreadName() {
        return threadName.get();
    }

    public void setThreadName(String threadName) {
        this.threadName.set(threadName);
    }

    public SimpleStringProperty threadNameProperty() {
        return threadName;
    }

    public String getThreadStatus() {
        return threadStatus.get();
    }

    public void setThreadStatus(String threadStatus) {
        this.threadStatus.set(threadStatus);
    }

    public SimpleStringProperty threadStatusProperty() {
        return threadStatus;
    }

    public int getThreadPriority() {
        return threadPriority.get();
    }

    public void setThreadPriority(int threadPriority) {
        this.threadPriority.set(threadPriority);
    }

    public SimpleIntegerProperty threadPriorityProperty() {
        return threadPriority;
    }

    public String getChangeTimeStatus() {
        return changeTimeStatus.get();
    }

    public void setChangeTimeStatus(String changeTimeStatus) {
        this.changeTimeStatus.set(changeTimeStatus);
    }

    public SimpleStringProperty changeTimeStatusProperty() {
        return changeTimeStatus;
    }
}
