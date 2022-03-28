package com.Taller1_RiveraJulian.service;

import com.Taller1_RiveraJulian.model.prod.Product;

public interface ProductService {
	
	public Product edit(Product pm);

	public Product save(Product p, Integer subcategory);

}
