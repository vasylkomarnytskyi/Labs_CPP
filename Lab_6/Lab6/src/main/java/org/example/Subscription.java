package org.example;
import java.io.Serializable;

public class Subscription implements Serializable {
    private String surname;
    private String name;
    private String email;
    private Reader reader;

    public Subscription(String surname, String name, String email, Reader reader) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.reader = reader;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "Subscription [Surname:" + this.surname +
                " ; Name: " + this.name +
                "; Email: " + this.email +
                "; \nReader:" + this.reader + "]\n";
    }
}
