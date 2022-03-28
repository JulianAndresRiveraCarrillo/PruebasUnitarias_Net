package com.Taller1_RiveraJulian.test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolverSupport;
import org.springframework.test.context.ContextConfiguration;

import com.Taller1_RiveraJulian.application.Taller1RiveraJulianApplication;
import com.Taller1_RiveraJulian.model.prod.Product;
import com.Taller1_RiveraJulian.model.prod.Productmodel;
import com.Taller1_RiveraJulian.model.prod.Productsubcategory;
import com.Taller1_RiveraJulian.repository.ProductRepository;
import com.Taller1_RiveraJulian.service.ProductServiceImp;

@ContextConfiguration(classes= Taller1RiveraJulianApplication.class)
@ExtendWith(MockitoExtension.class)
public class ProductTest {
	
	private Product p;
	private Productsubcategory s;
	
	@Mock
	private ProductRepository pr;

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
		
	}
	
	@AfterAll
	static void end() {
		System.out.println("=============================================");
		System.out.println("            ProductTest Finished");
		System.out.println("=============================================");
	}
}
