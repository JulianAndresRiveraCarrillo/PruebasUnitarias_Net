package com.Taller1_RiveraJulian.service;

import java.math.BigDecimal;

import com.Taller1_RiveraJulian.model.prod.Productcosthistory;
import com.Taller1_RiveraJulian.repository.ProductCostHistoryRepository;

public class ProductCostHistoryServiceImp implements ProductCostHistoryService{
	
	private ProductCostHistoryRepository pchr;
	
	public ProductCostHistoryServiceImp(ProductCostHistoryRepository pchr) {
		this.pchr = pchr;
	}

	@Override
	public void save(Productcosthistory pc) {
		if(pc.getProduct() != null && (pc.getStandardcost().compareTo(BigDecimal.ZERO) > 0 && pc.getModifieddate().before(pc.getEnddate()))) {
			pchr.save(pc);
		}
	}

	@Override
	public void edit(Productcosthistory pc) {
		if(pc.getProduct() != null && (pc.getStandardcost().compareTo(BigDecimal.ZERO) > 0 && pc.getModifieddate().before(pc.getEnddate()))) {
			Productcosthistory temp = pchr.getById(pc.getId());
			temp.setProduct(pc.getProduct());
			temp.setEnddate(pc.getEnddate());
			temp.setModifieddate(pc.getEnddate());
			temp.setStandardcost(pc.getStandardcost());
			pchr.save(temp);
		}
	}	

}
