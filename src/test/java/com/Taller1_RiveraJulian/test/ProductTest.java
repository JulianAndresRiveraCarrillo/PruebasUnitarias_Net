 package com.Taller1_RiveraJulian.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import com.Taller1_RiveraJulian.application.Taller1RiveraJulianApplication;
import com.Taller1_RiveraJulian.model.prod.Product;
import com.Taller1_RiveraJulian.model.prod.Productsubcategory;
import com.Taller1_RiveraJulian.repository.ProductRepository;
import com.Taller1_RiveraJulian.repository.ProductSubCategoryRepository;
import com.Taller1_RiveraJulian.service.ProductServiceImp;

@ContextConfiguration(classes= Taller1RiveraJulianApplication.class)
@ExtendWith(MockitoExtension.class)
public class ProductTest {
	
	private Product p;
	private Productsubcategory s;
	
	@Mock
	private ProductRepository pr;
	
	@Mock
	private ProductSubCategoryRepository sr;

	@InjectMocks
	private ProductServiceImp ps;
	
	
	@BeforeAll
	static void init() {
		System.out.println("=============================================");
		System.out.println("             ProductTest Started");
		System.out.println("=============================================");
	}
	
	@BeforeEach
	void setup() throws ParseException{
		p = new Product();
		s = new Productsubcategory();
		
		p.setName("New Product");
		p.setColor("Black");
		p.setDaystomanufacture(10);
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date  = format.parse("15-06-2022");
		Timestamp time = new Timestamp(date.getTime());
		p.setDiscontinueddate(time);
		p.setFinishedgoodsflag("ok");
		p.setListprice(new BigDecimal(15));
		p.setMakeflag("flag");
		
		Date date2  = format.parse("10-11-2022");
		Timestamp time2 = new Timestamp(date2.getTime());
		p.setModifieddate(time2);
		p.setProductline("first  line");
		p.setProductnumber("0000a");
		p.setReorderpoint(5);
		p.setRowguid(12);
		p.setSafetystocklevel(50);
		
		
		Date date3  = format.parse("10-01-2022");
		Timestamp time3 = new Timestamp(date3.getTime());
		p.setSellenddate(time3);
		
		Date date4  = format.parse("10-11-2022");
		Timestamp time4 = new Timestamp(date4.getTime());
		p.setSellstartdate(time4);
		p.setSize("big");
		p.setStandardcost(new BigDecimal(123));
		p.setStyle("cool");
		p.setWeight(new BigDecimal(50));
	}
	
	@Nested
	@DisplayName("Save for Product")
	class SaveProduct{
		
		@Test
		@DisplayName("Save test with correct values")
		void saveTest1() {
			when(sr.findById(1)).thenReturn(Optional.of(s));
			when(pr.save(p)).thenReturn(p);
			
			Product aux = ps.save(p);
			
			assertTrue(aux.getProductid() != null);
		}
	}
	
	@Nested
	@DisplayName("Edit for Product")
	class EditProduct{
		@Test
		@DisplayName("Edit test for correct values")
		void editTest1() throws ParseException {
			Product aux = new Product();
			aux.setName("Product");
			aux.setColor("Black");
			aux.setDaystomanufacture(10);
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = format.parse("15-01-2022");
			Timestamp time = new Timestamp(date.getTime());
			aux.setDiscontinueddate(time);
			aux.setFinishedgoodsflag("ok");
			aux.setListprice(new BigDecimal(15));
			aux.setMakeflag("flag");
			
			Date date2  = format.parse("10-11-2022");
			Timestamp time2 = new Timestamp(date2.getTime());
			aux.setModifieddate(time2);
			aux.setProductline("first  line");
			aux.setProductnumber("0000a");
			aux.setReorderpoint(5);
			aux.setRowguid(12);
			aux.setSafetystocklevel(50);
			
			
			Date date3  = format.parse("10-01-2022");
			Timestamp time3 = new Timestamp(date3.getTime());
			aux.setSellenddate(time3);
			
			Date date4  = format.parse("10-11-2022");
			Timestamp time4 = new Timestamp(date4.getTime());
			aux.setSellstartdate(time4);
			aux.setSize("big");
			aux.setStandardcost(new BigDecimal(123));
			aux.setStyle("cool");
			aux.setWeight(new BigDecimal(50));
			
			when(sr.findById(1)).thenReturn(Optional.of(s));
			when(pr.save(p)).thenReturn(p);
			
			Product temp = ps.save(aux);
			
			assertNotNull(temp);
		}
	}
	
	@AfterAll
	static void end() {
		System.out.println("=============================================");
		System.out.println("            ProductTest Finished");
		System.out.println("=============================================");
	}
}
