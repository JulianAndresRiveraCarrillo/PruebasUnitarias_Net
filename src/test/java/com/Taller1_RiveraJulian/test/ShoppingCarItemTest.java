package com.Taller1_RiveraJulian.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import com.Taller1_RiveraJulian.application.Taller1RiveraJulianApplication;
import com.Taller1_RiveraJulian.model.sales.Shoppingcartitem;
import com.Taller1_RiveraJulian.repository.ShoppingCarItemRepository;
import com.Taller1_RiveraJulian.service.ShoppingCarItemServiceImp;

@ContextConfiguration(classes= Taller1RiveraJulianApplication.class)
@ExtendWith(MockitoExtension.class)
public class ShoppingCarItemTest {
	
	private Shoppingcartitem sci;
	
	@Mock
	private ShoppingCarItemRepository scir;
	
	@InjectMocks
	private ShoppingCarItemServiceImp scis;
	
	@BeforeAll
	static void init() {
		System.out.println("============================================");
		System.out.println("      ShoppingCariTemTest Started");
		System.out.println("============================================");
	}
	
	@BeforeEach
	void setup() throws ParseException {
		sci = new Shoppingcartitem();
		sci.setQuantity(1000);
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date  = format.parse("01-11-2022");
		Timestamp time = new Timestamp(date.getTime());
		sci.setDatecreated(time);
		
		Date date2  = format.parse("29-11-2022");
		Timestamp time2 = new Timestamp(date2.getTime());
		sci.setModifieddate(time2);
	}
	
	@Nested
	@DisplayName("Save for Shopping Car Item")
	class SaveShoppingCarItem{
		@Test
		@DisplayName("Save test with correct values")
		void saveTest1() {
			when(scir.save(sci)).thenReturn(sci);
			
			Shoppingcartitem aux = scis.save(sci);
			
			assertTrue(aux.getProductid()!= null);
		}
	}
	
	@Nested
	@DisplayName("Edit for Shopping Car Item")
	class EditShoppingCarItem{
		@Test
		@DisplayName("Edit Test with correct values")
		void editTest() throws ParseException {
			Shoppingcartitem aux = new Shoppingcartitem();
			aux.setProductid(100);
			aux.setQuantity(1000);
			aux.setShoppingcartid("200");
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = format.parse("01-11-2022");
			Timestamp time = new Timestamp(date.getTime());
			aux.setDatecreated(time);
			
			Date date2  = format.parse("29-11-2022");
			Timestamp time2 = new Timestamp(date2.getTime());
			aux.setModifieddate(time2);
			
			when(scir.save(aux)).thenReturn(aux);
			
			Shoppingcartitem temp = scis.edit(aux);
			
			assertNotNull(temp);
		}
	}
	
	@AfterAll
	static void end() {
		System.out.println("============================================");
		System.out.println("      ShoppingCarItemTest Finished");
		System.out.println("============================================");
	}

}
