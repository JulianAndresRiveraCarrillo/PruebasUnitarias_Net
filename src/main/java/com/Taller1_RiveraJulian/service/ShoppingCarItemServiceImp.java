package com.Taller1_RiveraJulian.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taller1_RiveraJulian.model.sales.Shoppingcartitem;
import com.Taller1_RiveraJulian.repository.ShoppingCarItemRepository;

@Service
public class ShoppingCarItemServiceImp implements ShoppingCarItemService{
	
	private ShoppingCarItemRepository scir;
	
	@Autowired
	public ShoppingCarItemServiceImp(ShoppingCarItemRepository scir) {
		this.scir = scir;
	}

	@Override
	public Shoppingcartitem save(Shoppingcartitem item) {
		Shoppingcartitem temp = null;
		if(((scir.getById(item.getProductid()) != null) && ((item.getQuantity() > 0)) && (item.getDatecreated().before(new Date())))) {
			temp = scir.save(item);
		}
		return temp;
		
	}

	@Override
	public Shoppingcartitem edit(Shoppingcartitem item) {
		Shoppingcartitem temp = null;
		
		if(item.getShoppingcartid() != null) {
			Optional<Shoppingcartitem>optional = scir.findById(item.getProductid());
			if(optional.isPresent()) {
				temp = save(item);
			}
		}
		return temp;
	}

}
