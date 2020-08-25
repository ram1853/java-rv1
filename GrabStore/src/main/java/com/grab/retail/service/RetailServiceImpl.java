package com.grab.retail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grab.retail.dao.RetailDAO;
import com.grab.retail.entities.Retail;

@Service
public class RetailServiceImpl implements RetailService {

	@Autowired
	RetailDAO retailDao;

	@Override
	@Transactional
	public List<Retail> getRetailTypes() {

		return retailDao.getRetailTypes();
	}

	@Override
	@Transactional
	public Retail getRetail(int retailId) {

		return retailDao.getRetail(retailId);
	}

	@Override
	@Transactional
	public void addRetail(Retail retail) {

		retailDao.addRetail(retail);

	}

	@Override
	@Transactional
	public void deleteRetail(int retailId) {

		retailDao.deleteRetail(retailId);
	}

	@Override
	@Transactional
	public void modifyRetail(Object object, String mode) {

		if(!"add".equals(mode)) {
			retailDao.modifyRetail(object, mode);
		}else {
			/**
			 * IMPORTANT: NOT GOING TO HAVE ANY CODE FOR 'add'
			 * REASON: If you see the code in controller for add, we get the existing retail object
			 * and add the shirt or whatever object to its list.
			 * Now when the control comes to this method, here we have @Transactional annotation,
			 * this annotation will do 'auto-commiting' behind the scenes, which means 
			 * it is going to commit the transaction of adding shirt to the list, so no code required.
			 * Then due to cascade persist rule, the shirt object will be persisted to database. 
			 * This happens when hibernate flushes the session (synchronizing in-memory data with the database)
			 * Note that we don't perform any database operations of saving or updating retail,
			 * we just get the retail java object's list and add shirt to it, that's all
			 * You can explore further to use this approach to other methods as well..
			 * 
			 * Read that important concept of stackoverflow in controller class
			 * Gist: If you get an already persistent object and modify it in the code, you need not call any
			 * database operations like save, merge etc.. (that's the reason we did not call any methods for 'add')
			 * 
			 * For 'update' -> the incoming argument 'shirt' is not an already persistent object, hence we call the
			 * database operations.
			 * 
			 * Note: Hibernate will NOT update an object if no changes done.
			 */
		}

	}

}
