package com.grab.retail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grab.retail.entities.Pant;
import com.grab.retail.entities.Retail;
import com.grab.retail.entities.Shirt;
import com.grab.retail.entities.Shoe;
import com.grab.retail.service.RetailService;
import com.grab.retail.utils.RetailNotFoundException;

@RestController
@RequestMapping("/retail/{retailId}")
public class RetailController {

	@Autowired
	RetailService retailService;

	//Endpoints for Shirts
	/**
	 * Method: getAllShirts
	 * Description: Get all for a given retail type
	 * @param retailId
	 * @return
	 */
	@GetMapping("/shirts")
	public List<Shirt> getAllShirts(@PathVariable int retailId){

		Retail retail = retailService.getRetail(retailId);

		return retail.getShirts();
	}
	/**
	 * Method: getShirt
	 * Description: Get shirt for a given shirt ID
	 * @param retailId
	 * @param shirtId
	 * @return
	 */
	@GetMapping("/shirts/{shirtId}")
	public Shirt getShirt(@PathVariable int retailId, @PathVariable int shirtId) {

		Retail retail = retailService.getRetail(retailId);

		List<Shirt> shirts = retail.getShirts();

		Shirt shirt = isShirtExist(shirtId, shirts);
		if(shirt != null) {
			return shirt;
		}else {
			throw new RetailNotFoundException("Shirt not available with id: "+shirtId);
		}
	}
	/**
	 * Method: addShirt
	 * Description: Add a shirt for a given retail type
	 * @param retailId
	 * @param shirt
	 */
	@PostMapping("/shirts")
	public void addShirt(@PathVariable int retailId, @RequestBody Shirt shirt) {

		Retail retail = retailService.getRetail(retailId);
		retail.getShirts().add(shirt);
		/*
		 * When you update the retail, the existing retail object gets saved which in turn will save the shirt object also
		 * since cascade type is ALL.
		 */
		retailService.modifyRetail(retail, "add");
	}
	/**
	 * 
	 * *********IMPORTANT READ THIS CONCEPT************
	 * "https://stackoverflow.com/questions/49604134/update-vs-merge-method-in-hibernate"
	 */

	/**
	 * Method: updateShirt
	 * Description: Update shirt with a given Shirt ID
	 * @param retailId
	 * @param shirt
	 */
	@PutMapping("/shirts")
	public void updateShirt(@PathVariable int retailId, @RequestBody Shirt shirt) {

		Retail retail = retailService.getRetail(retailId);
		List<Shirt> shirts = retail.getShirts();
		Shirt existingShirt = isShirtExist(shirt.getId(), shirts);
		if(existingShirt != null) {
			/**
			 * Here we need not worry about the relation between retail and shirt object,
			 * because we are just updating an existing shirt, this will no way affect the relation between two tables,
			 * also we would have user 'merge' dao method 
			 */

			retailService.modifyRetail(shirt, "update");
		}else {
			throw new RetailNotFoundException("Shirt not found with id: "+shirt.getId());
		}

	}
	/**
	 * Method: deleteShirt
	 * Description: Delete shirt for a given shirt ID
	 * @param retailId
	 * @param shirtId
	 */
	@DeleteMapping("/shirts/{shirtId}")
	public void deleteShirt(@PathVariable int retailId, @PathVariable int shirtId) {

		Retail retail = retailService.getRetail(retailId);
		List<Shirt> shirts = retail.getShirts();
		Shirt existingShirt = isShirtExist(shirtId, shirts);
		if(existingShirt != null) {
			/**
			 * We have retail object and Shirt object
			 * shirt is present as a list in retail 
			 * which means retail's reference is present in shirt as 'type_id' in database table
			 * So here we first remove that reference and then delete the existing shirt
			 */
			shirts.remove(existingShirt);
			retailService.modifyRetail(existingShirt, "delete");
		}else {
			throw new RetailNotFoundException("Shirt not found with id: "+shirtId);
		}
	}
	/**
	 * Method: isShirtExist
	 * Description: Check if the shirt for a given Shirt ID already exist or not.
	 * @param shirtId
	 * @param shirts
	 * @return
	 */
	private Shirt isShirtExist(int shirtId, List<Shirt> shirts) {
		Shirt shirt = shirts.stream().filter(s -> shirtId == s.getId()).findAny().orElse(null);
		return shirt;
	}

	//Endpoints for Pants

	@GetMapping("/pants")
	public List<Pant> getAllPants(@PathVariable int retailId){

		Retail retail = retailService.getRetail(retailId);

		return retail.getPants();
	}

	@GetMapping("/pants/{pantId}")
	public Pant getPant(@PathVariable int retailId, @PathVariable int pantId) {

		Retail retail = retailService.getRetail(retailId);

		List<Pant> pants = retail.getPants();

		Pant pant = isPantExist(pantId, pants);
		if(pant != null) {
			return pant;
		}else {
			throw new RetailNotFoundException("Pant not available with id: "+pantId);
		}
	}

	@PostMapping("/pants")
	public void addPant(@PathVariable int retailId, @RequestBody Pant pant) {

		Retail retail = retailService.getRetail(retailId);
		retail.getPants().add(pant);
		retailService.modifyRetail(retail, "add");
	}

	@PutMapping("/pants")
	public void updatePant(@PathVariable int retailId, @RequestBody Pant pant) {

		Retail retail = retailService.getRetail(retailId);
		List<Pant> pants = retail.getPants();
		Pant existingPant = isPantExist(pant.getId(), pants);
		if(existingPant != null) {
			retailService.modifyRetail(pant, "update");
		}else {
			throw new RetailNotFoundException("Pant not found with id: "+pant.getId());
		}

	}

	@DeleteMapping("/pants/{pantId}")
	public void deletePant(@PathVariable int retailId, @PathVariable int pantId) {

		Retail retail = retailService.getRetail(retailId);
		List<Pant> pants = retail.getPants();
		Pant existingPant = isPantExist(pantId, pants);
		if(existingPant != null) {

			pants.remove(existingPant);
			retailService.modifyRetail(existingPant, "delete");
		}else {
			throw new RetailNotFoundException("Pant not found with id: "+pantId);
		}
	}
	/**
	 * Method: isPantExist
	 * Description: Check if a Pant exist for a given Pant ID
	 * @param pantId
	 * @param pants
	 * @return
	 */
	private Pant isPantExist(int pantId, List<Pant> pants) {
		Pant pant = pants.stream().filter(p -> pantId == p.getId()).findAny().orElse(null);
		return pant;
	}
	//Endpoint for Shoe

	@GetMapping("/shoes")
	public List<Shoe> getAllShoes(@PathVariable int retailId){

		Retail retail = retailService.getRetail(retailId);

		return retail.getShoes();
	}

	@GetMapping("/shoes/{shoeId}")
	public Shoe getShoe(@PathVariable int retailId, @PathVariable int shoeId) {

		Retail retail = retailService.getRetail(retailId);

		List<Shoe> shoes = retail.getShoes();

		Shoe shoe = isShoeExist(shoeId, shoes);
		if(shoe != null) {
			return shoe;
		}else {
			throw new RetailNotFoundException("Shoe not available with id: "+shoeId);
		}
	}

	@PostMapping("/shoes")
	public void addShoe(@PathVariable int retailId, @RequestBody Shoe shoe) {

		Retail retail = retailService.getRetail(retailId);
		retail.getShoes().add(shoe);
		retailService.modifyRetail(retail, "add");
	}

	@PutMapping("/shoes")
	public void updateShoe(@PathVariable int retailId, @RequestBody Shoe shoe) {

		Retail retail = retailService.getRetail(retailId);
		List<Shoe> shoes = retail.getShoes();
		Shoe existingShoe = isShoeExist(shoe.getId(), shoes);
		if(existingShoe != null) {
			retailService.modifyRetail(shoe, "update");
		}else {
			throw new RetailNotFoundException("Shoe not found with id: "+shoe.getId());
		}

	}

	@DeleteMapping("/shoes/{shoeId}")
	public void deleteShoe(@PathVariable int retailId, @PathVariable int shoeId) {

		Retail retail = retailService.getRetail(retailId);
		List<Shoe> shoes = retail.getShoes();
		Shoe existingShoe = isShoeExist(shoeId, shoes);
		if(existingShoe != null) {

			shoes.remove(existingShoe);
			retailService.modifyRetail(existingShoe, "delete");
		}else {
			throw new RetailNotFoundException("Shoe not found with id: "+shoeId);
		}
	}
	/**
	 * Method: isShoeExist
	 * Description: Check if a Shoe already exist for a given Shoe ID
	 * @param shoeId
	 * @param shoes
	 * @return
	 */
	private Shoe isShoeExist(int shoeId, List<Shoe> shoes) {
		Shoe shoe = shoes.stream().filter(s -> shoeId == s.getId()).findAny().orElse(null);
		return shoe;
	}

}
