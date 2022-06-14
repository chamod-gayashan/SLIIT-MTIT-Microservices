package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shipping")
public class Shipping {
	private long id;
	private String trackingNumber;
	private String itemName;
	private String address;
	private String shippingService;
	private String netPrice;
	
	public Shipping() {
		
	}
	
	public Shipping(String trackingNumber, String itemName, String address, String shippingService, String netPrice) {
		this.trackingNumber = trackingNumber;
		this.itemName = itemName;
		this.address = address;
		this.shippingService = shippingService;
		this.netPrice = netPrice;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "tracking_number", nullable = false)
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String name) {
		this.trackingNumber = name;
	}
	
	@Column(name = "item_name", nullable = false)
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String isbnNumber) {
		this.itemName = isbnNumber;
	}

	@Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}
	public void setAddress(String price) {
		this.address = price;
	}

	@Column(name = "shipping_service", nullable = false)
	public String getShippingService() {
		return shippingService;
	}
	public void setShippingService(String author) {
		this.shippingService = author;
	}
	
	@Column(name = "net_price", nullable = false)
	public String getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(String genre) {
		this.netPrice = genre;
	}

	@Override
	public String toString() {
		return "Shipping [id=" + id + ", trackingNumber=" + trackingNumber + ", itemName=" + itemName + ",address=" + address + ",shippingService="+ shippingService + " netPrice=" + netPrice
				+ "]";
	}

}
