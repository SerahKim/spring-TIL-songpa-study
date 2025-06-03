package com.section01.common;

import java.util.Date;

public class BookDTO {
    private int sequence;
    private int isbn;
    private String title;
    private String autor;
    private String publisher;
    private Date createdDate;

    public BookDTO() {}

    public BookDTO(int sequence, int isbn, String title, String autor, String publisher, Date createdDate) {
        this.sequence = sequence;
        this.isbn = isbn;
        this.title = title;
        this.autor = autor;
        this.publisher = publisher;
        this.createdDate = createdDate;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "sequence=" + sequence +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", autor='" + autor + '\'' +
                ", publisher='" + publisher + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
