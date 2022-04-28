package com.Taller1_RiveraJulian.service;

import com.Taller1_RiveraJulian.model.prod.Productcosthistory;
import com.Taller1_RiveraJulian.model.prod.ProductcosthistoryPK;

public interface ProductCostHistoryService {

	public Productcosthistory save(Productcosthistory pc);
	
	public Productcosthistory edit(Productcosthistory pc, ProductcosthistoryPK id);
}
