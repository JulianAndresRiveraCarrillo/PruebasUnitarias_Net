package com.Taller1_RiveraJulian.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taller1_RiveraJulian.model.prod.Product;
import com.Taller1_RiveraJulian.model.prod.Productcategory;
import com.Taller1_RiveraJulian.model.prod.Productsubcategory;
import com.Taller1_RiveraJulian.repository.ProductCategoryRepository;
import com.Taller1_RiveraJulian.repository.ProductRepository;
import com.Taller1_RiveraJulian.repository.ProductSubCategoryRepository;

@Service
public class ProductServiceImp implements ProductService{

	private ProductRepository pr;
	private ProductSubCategoryRepository pscr;
	private ProductCategoryRepository pcr;
	
	@Autowired
	public ProductServiceImp(ProductRepository pr, ProductSubCategoryRepository pscr, ProductCategoryRepository pcr) {
		this.pr = pr;
		this.pscr = pscr;
		this.pcr = pcr;
	}
	
	@Override
	public Product save(Product p) {
		Product aux = null;
		
		if(p.getProductnumber() != null && p.getSellstartdate().before(p.getSellenddate())) {
			int size = Integer.parseInt(p.getSize());
			if(size > 0 && p.getWeight().compareTo(BigDecimal.ZERO) > 0) {
				Optional<Productsubcategory> optional = pscr.findById(p.getProductsubcategory().getProductsubcategoryid());
				Optional<Productcategory> optional2= pcr.findById(p.getProductsubcategory().getProductcategory().getProductcategoryid());
				if(optional.isPresent() && optional2.isPresent()) {
					p.setProductsubcategory(optional.get());
					p.getProductsubcategory().setProductcategory(optional2.get());
					
					aux = pr.save(p);
				}
			}
		}
		
		return aux;
	}

	@Override
	public Product edit(Product p) {
		Product aux = null;
		if(p.getProductid() != null) {
			Optional<Product> optional = pr.findById(p.getProductid());
			if(optional.isPresent()) {
				aux = save(p);
			}
		}
		/*if (p.getProductnumber() != null && p.getSellstartdate().before(p.getSellenddate())) {
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
		}*/
		return aux;
		
	}

}
