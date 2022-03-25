package com.example.bookclub.Models;

public class SummaryModel {
    private int id, userId, bookID, visible;
    private String summary;

    public SummaryModel(int id, int userId, int bookID, int visible, String summary) {
        this.id = id;
        this.userId = userId;
        this.bookID = bookID;
        this.visible = visible;
        this.summary = summary;
    }

    public SummaryModel(int userId, int bookID, int visible, String summary) {
        this.userId = userId;
        this.bookID = bookID;
        this.visible = visible;
        this.summary = summary;
    }

    public SummaryModel(int userId, int bookID, String summary) {
        this.userId = userId;
        this.bookID = bookID;
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "SummaryModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookID=" + bookID +
                ", visible=" + visible +
                ", summary='" + summary + '\'' +
                '}';
    }
}
