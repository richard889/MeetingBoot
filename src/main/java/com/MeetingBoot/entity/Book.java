package com.MeetingBoot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "bookCategory_id")
	private BookCategory bookCategory;

    public Book() {
    }
    public Book(String name) {
    	this.name = name;
    }
    public Book(String name, BookCategory bookCategory) {
    	this.name = name;
    	this.bookCategory = bookCategory;
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BookCategory getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}
}
