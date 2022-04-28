package com.Taller1_RiveraJulian.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taller1_RiveraJulian.model.prod.Productmodel;
import com.Taller1_RiveraJulian.repository.ProductModelRepository;

@Service
public class ProductModelServiceImp implements ProductModelService{

	private ProductModelRepository pmr;
	
	@Autowired
	public ProductModelServiceImp(ProductModelRepository pmr) {
		this.pmr = pmr;
	}
	
	@Override
	public Productmodel save(Productmodel pm) throws RuntimeException {
		Productmodel aux = null;
		if (pm.getName().length() < 5) {
			throw new RuntimeException("La longitud del nombre debe ser mayor a 5");
		}
		else if(pm.getCatalogdescription().length() < 5) {
			throw new RuntimeException("La longitud de la descripcion del catalogo debe ser mayor a 5");
		}
		else {
			aux = pmr.save(pm);
		}
		
		return aux;
	}

	@Override
	public Productmodel edit(Productmodel pm) {
		Productmodel aux = null;
		
		if(pm.getProductmodelid() != null) {
			Optional<Productmodel>optional = pmr.findById(pm.getProductmodelid());
			if(optional.isPresent()) {
				aux = save(pm);
			}
		}
		
		return aux;
	}

}
