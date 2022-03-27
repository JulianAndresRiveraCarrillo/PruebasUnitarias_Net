package com.Taller1_RiveraJulian.service;

import java.util.Date;

import com.Taller1_RiveraJulian.model.sales.Shoppingcartitem;
import com.Taller1_RiveraJulian.repository.ShoppingCarItemRepository;

public class ShoppingCarItemServiceImp implements ShoppingCarItemService{
	
	private ShoppingCarItemRepository scir;
	
	public ShoppingCarItemServiceImp(ShoppingCarItemRepository scir) {
		this.scir = scir;
	}

	@Override
	public void save(Shoppingcartitem item) {
		if((scir.getById(item.getProductid()) != null) && ((item.getQuantity() > 0) && (item.getDatecreated().before(new Date())))) {
			scir.save(item);
		}
		
	}

	@Override
	public void edit(Shoppingcartitem item) {
		if((scir.getById(item.getProductid()) != null) && ((item.getQuantity() > 0) && (item.getDatecreated().before(new Date())))) {
			Shoppingcartitem temp = scir.getById(item.getShoppingcartitemid());
			temp.setDatecreated(item.getDatecreated());
			temp.setModifieddate(item.getModifieddate());
			temp.setProductid(item.getProductid());
			temp.setQuantity(item.getQuantity());
			temp.setShoppingcartid(item.getShoppingcartid());
			scir.save(temp);
		}
	}

}
