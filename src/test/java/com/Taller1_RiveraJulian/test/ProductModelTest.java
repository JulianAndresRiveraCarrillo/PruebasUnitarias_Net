package com.Taller1_RiveraJulian.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.Taller1_RiveraJulian.model.prod.Productmodel;
import com.Taller1_RiveraJulian.repository.ProductModelRepository;
import com.Taller1_RiveraJulian.service.ProductModelServiceImp;

public class ProductModelTest {

	private Productmodel pm;
	
	@Mock
	private ProductModelRepository pmr;
	
	@InjectMocks
	private ProductModelServiceImp pms;
	
	@BeforeAll
	static void init() {
		System.out.println("============================================");
		System.out.println("        ProductModelTest Started");
		System.out.println("============================================");
	}
	
	@BeforeEach
	void setup() throws ParseException {
		pm = new Productmodel();
		pm.setCatalogdescription("New Catalog Description");
		pm.setInstructions("New set of Instructions");
		
		SimpleDateFormat format = new SimpleDateFormat("DD-MM-YYYY");
		Date date = format.parse("27-03-2022");
		Timestamp time = new Timestamp(date.getTime());
		pm.setModifieddate(time);
		pm.setName("Product Model 01");
		
	}
	
	@Nested
	@DisplayName("Save for Product Model")
	class SaveProductModel{
		
		@Test
		void saveTest1() {
			when(pmr.save(pm)).thenReturn(pm);
			
			Productmodel aux = pms.save(pm);
			
			assertTrue(aux.getName().length() > 0);
			assertTrue(aux.getCatalogdescription().length() > 0);
		}
	}
	
	@AfterAll
	static void end() {
		System.out.println("============================================");
		System.out.println("         ProductModelTest Finished");
		System.out.println("============================================");
	}
}
