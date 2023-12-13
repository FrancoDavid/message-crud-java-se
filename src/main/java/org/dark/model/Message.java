package org.dark.model;

public class Message {

    private Integer id;
    private String message;
    private String author;
    private String date;

    public Message() {
    }

    public Message(Integer id, String message, String author, String date) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return "Message: [" + id + "] "
                + message
                + " ----- "
                + author + " / " + date;
    }
}
