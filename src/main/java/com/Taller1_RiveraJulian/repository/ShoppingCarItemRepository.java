package com.Taller1_RiveraJulian.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Taller1_RiveraJulian.model.sales.Shoppingcartitem;

public interface ShoppingCarItemRepository extends JpaRepository<Shoppingcartitem, Integer> {

}
