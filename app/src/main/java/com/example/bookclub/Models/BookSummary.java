package com.example.bookclub.Models;

public class BookSummary {
    private String name, summary;

    public BookSummary(String name, String summary) {
        this.name = name;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "BookSummary{" +
                "name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
