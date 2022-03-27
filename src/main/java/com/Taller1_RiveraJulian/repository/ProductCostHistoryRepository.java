package com.Taller1_RiveraJulian.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Taller1_RiveraJulian.model.prod.Productcosthistory;
import com.Taller1_RiveraJulian.model.prod.ProductcosthistoryPK;

public interface ProductCostHistoryRepository extends JpaRepository<Productcosthistory, ProductcosthistoryPK> {

}
