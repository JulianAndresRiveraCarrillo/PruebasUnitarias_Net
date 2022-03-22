package com.Taller1_RiveraJulian.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Taller1_RiveraJulian.model.prod.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
