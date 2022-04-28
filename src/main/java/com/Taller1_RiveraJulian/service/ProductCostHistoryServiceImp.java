	package com.Taller1_RiveraJulian.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taller1_RiveraJulian.model.prod.Productcosthistory;
import com.Taller1_RiveraJulian.model.prod.ProductcosthistoryPK;
import com.Taller1_RiveraJulian.repository.ProductCostHistoryRepository;

@Service
public class ProductCostHistoryServiceImp implements ProductCostHistoryService{
	
	private ProductCostHistoryRepository pchr;
	
	@Autowired
	public ProductCostHistoryServiceImp(ProductCostHistoryRepository pchr) {
		this.pchr = pchr;
	}

	@Override
	public Productcosthistory save(Productcosthistory pc) throws RuntimeException {
		Productcosthistory aux = null;
		if(pc.getProduct() == null) {
			throw new RuntimeException("El producto no existe");
		}else if(pc.getStandardcost().compareTo(BigDecimal.ZERO) <= 0){
			throw new RuntimeException("El Costo no es mayor a cero");
		}else if(pc.getModifieddate().after(pc.getEnddate())) {
			throw new RuntimeException("La fecha de modifcacion debe ser menor que la fecha de fin");
		}else {
			aux = pchr.save(pc);
		}
		
		return aux;
	}

	@Override
	public Productcosthistory edit(Productcosthistory pc, ProductcosthistoryPK id) {
		Productcosthistory aux = null;
		Optional<Productcosthistory> optional = pchr.findById(id);
		Productcosthistory temp = optional.get();
	
		if(temp != null) {
			temp.setEnddate(pc.getEnddate());
			temp.setModifieddate(pc.getModifieddate());
			temp.setProduct(pc.getProduct());
			temp.setStandardcost(pc.getStandardcost());
			aux = save(temp);
		}
		
		return aux;
	}	

}
