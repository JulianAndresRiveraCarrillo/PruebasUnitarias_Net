	package com.Taller1_RiveraJulian.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taller1_RiveraJulian.model.prod.Productcosthistory;
import com.Taller1_RiveraJulian.repository.ProductCostHistoryRepository;

@Service
public class ProductCostHistoryServiceImp implements ProductCostHistoryService{
	
	private ProductCostHistoryRepository pchr;
	
	@Autowired
	public ProductCostHistoryServiceImp(ProductCostHistoryRepository pchr) {
		this.pchr = pchr;
	}

	@Override
	public Productcosthistory save(Productcosthistory pc) {
		Productcosthistory aux = null;
		if(pc.getProduct() != null && (pc.getStandardcost().compareTo(BigDecimal.ZERO) > 0 && pc.getModifieddate().before(pc.getEnddate()))) {
			aux = pchr.save(pc);
		}
		
		return aux;
	}

	@Override
	public Productcosthistory edit(Productcosthistory pc) {
		Productcosthistory aux = null;
		if(pc.getId() != null) {
			Optional<Productcosthistory> optional = pchr.findById(pc.getId());
			if(optional.isPresent()) {
				aux = save(pc);
			}
		}
		return aux;
	}	

}
