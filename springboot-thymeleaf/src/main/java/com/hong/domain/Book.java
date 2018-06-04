package com.hong.domain;

/**
 * Created by hong on 2017/4/21.
 */
public class Book {

    private Integer id;

    private String bookName;

    private Double price;

    public Book(Integer id, String bookName,Double price) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
