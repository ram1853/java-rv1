package com.grab.retail.dao;

import java.util.List;

import com.grab.retail.entities.Retail;

public interface RetailDAO {
	
	public List<Retail> getRetailTypes();
	
	public Retail getRetail(int retailId);
	
	public void addRetail(Retail retail);
	
	public void modifyRetail(Object object, String mode);
	
	public void deleteRetail(int retailId);
}
