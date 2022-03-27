package com.Taller1_RiveraJulian.service;

import java.math.BigDecimal;

import com.Taller1_RiveraJulian.model.prod.Product;
import com.Taller1_RiveraJulian.repository.ProductCategoryRepository;
import com.Taller1_RiveraJulian.repository.ProductRepository;
import com.Taller1_RiveraJulian.repository.ProductSubCategoryRepository;

public class ProductServiceImp implements ProductService{

	private ProductRepository pr;
	private ProductSubCategoryRepository pscr;
	private ProductCategoryRepository pcr;
	
	public ProductServiceImp(ProductRepository pr, ProductSubCategoryRepository pscr, ProductCategoryRepository pcr) {
		this.pr = pr;
		this.pscr = pscr;
		this.pcr = pcr;
	}
	
	@Override
	public void save(Product p) {
		if (p.getProductnumber() != null && p.getSellstartdate().before(p.getSellenddate())) {
			int size = Integer.parseInt(p.getSize());
			if((size > 0 && p.getWeight().compareTo(BigDecimal.ZERO) > 0) && (pscr.getById(p.getProductsubcategory().getProductsubcategoryid()) != null && pcr.getById(p.getProductsubcategory().getProductcategory().getProductcategoryid()) != null)) {
				pr.save(p);
			}
		}
	}

	@Override
	public void edit(Product p) {
		if (p.getProductnumber() != null && p.getSellstartdate().before(p.getSellenddate())) {
			int size = Integer.parseInt(p.getSize());
			if((size > 0 && p.getWeight().compareTo(BigDecimal.ZERO) > 0) && (pscr.getById(p.getProductsubcategory().getProductsubcategoryid()) != null && pcr.getById(p.getProductsubcategory().getProductcategory().getProductcategoryid()) != null)) {
				Product  temp= pr.getById(p.getProductid());
				temp.setProductnumber(p.getProductnumber());
				temp.setSellenddate(p.getSellenddate());
				temp.setSellstartdate(p.getSellstartdate());
				temp.setSize(p.getSize());
				temp.setWeight(p.getWeight());
				temp.setProductsubcategory(p.getProductsubcategory());
				temp.getProductsubcategory().setProductcategory(p.getProductsubcategory().getProductcategory());
				pr.save(temp);
			}
		}
		
	}

}
