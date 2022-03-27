package com.Taller1_RiveraJulian.service;

import java.util.Optional;

import com.Taller1_RiveraJulian.model.prod.Productmodel;
import com.Taller1_RiveraJulian.repository.ProductModelRepository;

public class ProductModelServiceImp implements ProductModelService{

	private ProductModelRepository pmr;
	
	public ProductModelServiceImp(ProductModelRepository pmr) {
		this.pmr = pmr;
	}
	@Override
	public Productmodel save(Productmodel pm) {
		Productmodel aux = null;
		if (pm.getName().length() >= 5 && pm.getCatalogdescription().length() >= 5) {
			Optional<Productmodel> optional = this.pmr.findById(pm.getProductmodelid());
			if(optional.isPresent()) {
				aux = this.pmr.save(pm);
			}
		}
		return aux;
	}

	@Override
	public Productmodel edit(Productmodel pm) {
		Productmodel aux = null;
		if (pm.getName().length() >= 5 && pm.getCatalogdescription().length() >= 5) {
			Optional<Productmodel> optional = pmr.findById(pm.getProductmodelid());
			if(optional.isPresent()) {
				/*temp.setName(pm.getName());
				temp.setCatalogdescription(pm.getCatalogdescription());
				temp.setInstructions(pm.getInstructions());
				temp.setModifieddate(pm.getModifieddate());
				temp.setRowguid(pm.getRowguid());*/
				aux = pmr.save(pm);
			}	
		}
		return aux;
	}

}
