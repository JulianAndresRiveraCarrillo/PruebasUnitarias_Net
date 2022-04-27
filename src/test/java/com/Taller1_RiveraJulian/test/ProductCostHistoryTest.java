package com.Taller1_RiveraJulian.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.Taller1_RiveraJulian.model.prod.Product;
import com.Taller1_RiveraJulian.model.prod.Productcosthistory;
import com.Taller1_RiveraJulian.repository.ProductCostHistoryRepository;
import com.Taller1_RiveraJulian.service.ProductCostHistoryServiceImp;

@ContextConfiguration(classes= Taller1RiveraJulianApplication.class)
@ExtendWith(MockitoExtension.class)
public class ProductCostHistoryTest {

	private Productcosthistory pch;
	private Product p;
	
	@Mock
	private ProductCostHistoryRepository pchr;
	
	@InjectMocks
	private ProductCostHistoryServiceImp pchs;
	
	@BeforeAll
	static void init() {
		System.out.println("============================================");
		System.out.println("    ProductCostHistoryTest Started");
		System.out.println("============================================");
	}
	
	@BeforeEach
	void setup() throws ParseException {
		pch = new Productcosthistory();
		p = new Product();
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date  = format.parse("26-04-2022");
		Timestamp time = new Timestamp(date.getTime());
		pch.setEnddate(time);
		
		Date date2  = format.parse("20-04-2022");
		Timestamp time2 = new Timestamp(date2.getTime());
		pch.setModifieddate(time2);
		
		pch.setStandardcost(new BigDecimal(100));
		
		pch.setProduct(p);
	}
	
	@Nested
	@DisplayName("Save for ProductCostHistory")
	class SaveCostHistory{
		
		@Test
		@DisplayName("Save test with correct values")
		void saveTest1() {
			when(pchr.save(pch)).thenReturn(pch);
			
			Productcosthistory temp = pchs.save(pch);
			
			assertEquals(new BigDecimal(100), temp.getStandardcost());
		}
		
		@Test
		@DisplayName("Save test with correct values")
		void saveTest2() {
			when(pchr.save(pch)).thenReturn(pch);
			
			Productcosthistory temp = pchs.save(pch);
			
			assertNotNull(temp.getProduct());
		}
		
		@Test
		@DisplayName("Save test with correct values")
		void saveTest3() {
			when(pchr.save(pch)).thenReturn(pch);
			
			Productcosthistory temp = pchs.save(pch);
			
			assertTrue(pch.getModifieddate().before(pch.getEnddate()));
		}
		
		@Test
		@DisplayName("Save test with product null")
		void saveTest4() {
			when(pchr.save(pch)).thenReturn(pch);
			
			Productcosthistory temp = pchs.save(pch);
			temp.setProduct(null);
			
			assertNull(temp.getProduct());
		}
		
		@Test
		@DisplayName("Save test with error on dates")
		void saveTest5() throws ParseException {
			when(pchr.save(pch)).thenReturn(pch);
			
			Productcosthistory temp = pchs.save(pch);
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = format.parse("19-04-2022");
			Timestamp time = new Timestamp(date.getTime());
			temp.setEnddate(time);
			
			Date date2  = format.parse("22-04-2022");
			Timestamp time2 = new Timestamp(date2.getTime());
			temp.setModifieddate(time2);
			
			assertFalse(pch.getModifieddate().before(pch.getEnddate()));
		}
			
	}
	
	@Nested
	@DisplayName("Edit for ProductCostHistory")
	class EditCostHistory{
		
		@Test
		@DisplayName("Edit test not Null")
		void editTest1() throws ParseException {
			Productcosthistory aux = new Productcosthistory();
			Product pr = new Product();
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = format.parse("20-04-2022");
			Timestamp time = new Timestamp(date.getTime());
			aux.setEnddate(time);
			
			Date date2  = format.parse("20-03-2022");
			Timestamp time2 = new Timestamp(date2.getTime());
			aux.setModifieddate(time2);
			
			aux.setStandardcost(new BigDecimal(100));
			
			aux.setProduct(p);
			
			when(pchr.save(aux)).thenReturn(aux);
			
			Productcosthistory temp = pchs.save(aux);
			
			assertNotNull(temp);
		}
	}
}
