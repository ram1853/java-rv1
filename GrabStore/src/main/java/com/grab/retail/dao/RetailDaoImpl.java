package com.grab.retail.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grab.retail.entities.Pant;
import com.grab.retail.entities.Retail;
import com.grab.retail.entities.Shirt;
import com.grab.retail.entities.Shoe;
import com.grab.retail.utils.RetailNotFoundException;

@Repository
public class RetailDaoImpl implements RetailDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Retail> getRetailTypes() {

		Session session = entityManager.unwrap(Session.class);

		return session.createQuery("from Retail", Retail.class).getResultList();
	}

	@Override
	public Retail getRetail(int retailId) {

		Session session = entityManager.unwrap(Session.class);
		Retail retail = session.get(Retail.class, retailId);
		if(retail != null) {
			return retail;
		}else {
			throw new RetailNotFoundException("Retail not found with id: "+retailId);
		}
	}

	@Override
	public void addRetail(Retail retail) {

		Session session = entityManager.unwrap(Session.class);
		retail.setId(0);
		session.saveOrUpdate(retail);

	}

	@Override
	public void deleteRetail(int retailId) {

		Session session = entityManager.unwrap(Session.class);
		Retail retail = session.get(Retail.class, retailId);
		if(retail != null) {
			session.delete(retail);
		}else {
			throw new RetailNotFoundException("Retail not found with id: "+retailId);
		}

	}

	@Override
	public void modifyRetail(Object object, String mode) {
		
		Session session = entityManager.unwrap(Session.class);
		if(object instanceof Shirt) {
			
			Shirt shirt = (Shirt)object;
			
			if("delete".equals(mode)) {
				session.delete(shirt);
			}else if("update".equals(mode)) {
				session.merge(shirt);
			}
		}else if(object instanceof Pant) {

			Pant pant = (Pant)object;
			
			if("delete".equals(mode)) {
				session.delete(pant);
			}else if("update".equals(mode)) {
				session.merge(pant);
			}
		
		}else if(object instanceof Shoe) {

			Shoe shoe = (Shoe)object;
			
			if("delete".equals(mode)) {
				session.delete(shoe);
			}else if("update".equals(mode)) {
				session.merge(shoe);
			}
		
		
		}
		
		
		
	}

}
