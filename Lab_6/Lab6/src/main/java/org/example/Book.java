package org.example;
import java.io.Serializable;

public class Book implements Serializable{
    private String author;
    private String name;
    private int published;

    public Book(String author, String name, int published) {
        this.author = author;
        this.name = name;
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublished() {
        return published;
    }

    public void Published(int published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "\nBook [Name: " + this.name +
                ". Author: " + this.author +
                ". Published: " + this.published + ']';
    }
}
