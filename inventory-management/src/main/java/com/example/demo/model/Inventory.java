package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
	private long id;
	private String name;
	private String isbnNumber;
	private String price;
	private String author;
	private String genre;
	
	public Inventory() {
		
	}
	
	public Inventory(String name, String isbnNumber, String price, String author, String genre) {
		this.name = name;
		this.isbnNumber = isbnNumber;
		this.price = price;
		this.author = author;
		this.genre = genre;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "isbn_number", nullable = false)
	public String getIsbnNumber() {
		return isbnNumber;
	}
	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	@Column(name = "price", nullable = false)
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "author", nullable = false)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column(name = "genre", nullable = false)
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", name=" + name + ", isbnNumber=" + isbnNumber + ",gender=" + price + ",author="+ author + " genre=" + genre
				+ "]";
	}

}
