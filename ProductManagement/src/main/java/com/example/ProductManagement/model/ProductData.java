package com.example.ProductManagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_PRODUCT_DATA")
public class ProductData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "CODE")
	private String code;
	@Column(name = "NAME")	
	private String name;
	@Column(name = "BATCH")
	private String batch;
	@Column(name = "STOCK")
	private int stock;
	@Column(name = "DEAL")
	private int deal;
	@Column(name = "FREE")
	private int free;
	@Column(name = "MRP")
	private float mrp;
	@Column(name = "RATE")
	private float rate;
	@Column(name = "EXPIREDATE")
	private Date expiredate;
	@Column(name = "COMPANY")
	private String company;
	@Column(name = "SUPPLIER")
	private String supplier;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getDeal() {
		return deal;
	}
	public void setDeal(int deal) {
		this.deal = deal;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public float getMrp() {
		return mrp;
	}
	public void setMrp(float mrp) {
		this.mrp = mrp;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public Date getExpiredate() {
		return expiredate;
	}
	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
}
