package com.example.bookclub.Models;

public class BookModel {
    private int id;
    private String name, author, urlPhoto;

    public BookModel(String name, String author, String urlPhoto) {
        this.name = name;
        this.author = author;
        this.urlPhoto = urlPhoto;
    }

    public BookModel(int id, String name, String author, String urlPhoto) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.urlPhoto = urlPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", urlPhoto='" + urlPhoto + '\'' +
                '}';
    }
}
