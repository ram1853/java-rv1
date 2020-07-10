package com.grab.retail.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "retail_type")
public class Retail {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "category")
	private String category;
	
	@JoinColumn(name = "type_id")
	@OneToMany(cascade = {CascadeType.PERSIST})
	private List<Shirt> shirts;
	
	@JoinColumn(name = "type_id")
	@OneToMany(cascade = {CascadeType.PERSIST})
	private List<Pant> pants;
	
	@JoinColumn(name = "type_id")
	@OneToMany(cascade = {CascadeType.PERSIST})
	private List<Shoe> shoes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Shirt> getShirts() {
		return shirts;
	}

	public void setShirts(List<Shirt> shirts) {
		this.shirts = shirts;
	}

	public List<Pant> getPants() {
		return pants;
	}

	public void setPants(List<Pant> pants) {
		this.pants = pants;
	}

	public List<Shoe> getShoes() {
		return shoes;
	}

	public void setShoes(List<Shoe> shoes) {
		this.shoes = shoes;
	}
	
	
	
}
