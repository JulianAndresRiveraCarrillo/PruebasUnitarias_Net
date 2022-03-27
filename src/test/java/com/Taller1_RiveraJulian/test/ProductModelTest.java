package com.Taller1_RiveraJulian.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.Taller1_RiveraJulian.model.prod.Productmodel;
import com.Taller1_RiveraJulian.repository.ProductModelRepository;
import com.Taller1_RiveraJulian.service.ProductModelServiceImp;

@ContextConfiguration(classes= Taller1RiveraJulianApplication.class)
@ExtendWith(MockitoExtension.class)
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
		@DisplayName("Save test with correct values")
		void saveTest1() {
			when(pmr.save(pm)).thenReturn(pm);
			
			Productmodel aux = pms.save(pm);
			
			assertTrue(aux.getName().length() > 5);
			assertTrue(aux.getCatalogdescription().length() > 5);
		}
		
		@Test
		@DisplayName("Save test with wrong Catalog Description")
		void saveTest2() {

			try {
				pm.setCatalogdescription("null");
			} catch (RuntimeException e) {
				Throwable ex = assertThrows(RuntimeException.class, () -> pm.getCatalogdescription());
				assertEquals("Longitud minima" + " CatalogDexcription: " + "5 caracteres", ex.getMessage());
			}
			
			verify(pmr, times(0)).save(pm);
		}
		
		@Test
		@DisplayName("Save test with wrong Name")
		void saveTest3() {

			try {
				pm.setName("null");
			} catch (RuntimeException e) {
				Throwable ex = assertThrows(RuntimeException.class, () -> pm.getName());
				assertEquals("Longitud minima" + " Name: " + "5 caracteres", ex.getMessage());
			}
			
			verify(pmr, times(0)).save(pm);
		}
		
		@Test
		@DisplayName("Save test with Name null")
		void saveTest4() {
			try {
				pm.setName(null);
			} catch (RuntimeException e) {
				Throwable ex = assertThrows(RuntimeException.class, () -> pm.getName());
				assertEquals("Longitud minima" + " Name: " + "5 caracteres", ex.getMessage());
				assertNull(pm.getName());
			}
			
			verify(pmr, times(0)).save(pm);
		}
		
		@Test
		@DisplayName("Save test with Catalog Desription null")
		void saveTest5() {
			try {
				pm.setCatalogdescription(null);
			} catch (RuntimeException e) {
				Throwable ex = assertThrows(RuntimeException.class, () -> pm.getCatalogdescription());
				assertEquals("Longitud minima" + " Name: " + "5 caracteres", ex.getMessage());
				assertNull(pm.getCatalogdescription());
			}
			
			verify(pmr, times(0)).save(pm);
		}
	}

	
	@AfterAll
	static void end() {
		System.out.println("============================================");
		System.out.println("         ProductModelTest Finished");
		System.out.println("============================================");
	}
}
