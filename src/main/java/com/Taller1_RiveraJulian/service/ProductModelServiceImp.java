package com.Taller1_RiveraJulian.service;

import com.Taller1_RiveraJulian.model.prod.Productmodel;
import com.Taller1_RiveraJulian.repository.ProductModelRepository;

public class ProductModelServiceImp implements ProductModelService{

	private ProductModelRepository pmr;
	
	public ProductModelServiceImp(ProductModelRepository pmr) {
		this.pmr = pmr;
	}
	@Override
	public void save(Productmodel pm) {
		if (pm.getName().length() >= 5 && pm.getCatalogdescription().length() >= 5) {
			pmr.save(pm);	
		}
	}

	@Override
	public void edit(Productmodel pm) {
		// TODO Auto-generated method stub
		if (pm.getName().length() >= 5 && pm.getCatalogdescription().length() >= 5) {
			Productmodel temp= pmr.getById(pm.getProductmodelid());
			temp.setName(pm.getName());
			temp.setCatalogdescription(pm.getCatalogdescription());
			temp.setInstructions(pm.getInstructions());
			temp.setModifieddate(pm.getModifieddate());
			temp.setRowguid(pm.getRowguid());
			pmr.save(temp);	
		}
	}

}
