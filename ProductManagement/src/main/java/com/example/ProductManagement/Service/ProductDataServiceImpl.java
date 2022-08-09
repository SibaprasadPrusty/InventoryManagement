package com.example.ProductManagement.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.ProductManagement.Exception.ProductNotFoundException;
import com.example.ProductManagement.Repository.ProductRepository;
import com.example.ProductManagement.model.ProductData;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;


@Service
@Transactional
public class ProductDataServiceImpl implements ProductDataService{

	@Autowired
	private ProductRepository productrepository;
	
	@PersistenceContext
	private EntityManager entityManager; 
	
	@Override
	public String insertData(MultipartFile File) throws IOException,Exception {
		List<ProductData> ListProduct =new ArrayList<>();
	
		SimpleDateFormat formatter1=new SimpleDateFormat("dd-MM-yyyy");  
		InputStream	inputstream	= File.getInputStream();
		CsvParserSettings settings=new CsvParserSettings();
		settings.setHeaderExtractionEnabled(true);
		CsvParser parser= new CsvParser(settings);
		List<Record> parseAllRecords =parser.parseAllRecords(inputstream);
		parseAllRecords.forEach(record->{
			try {
			ProductData productdata = new ProductData();
			productdata.setCode(record.getString("code"));
			productdata.setName(record.getString("name"));
			productdata.setBatch(record.getString("batch"));
			productdata.setStock(Integer.parseInt(record.getString("stock")));
			productdata.setDeal(Integer.parseInt(record.getString("deal")));
			productdata.setFree(Integer.parseInt(record.getString("free")));
			productdata.setMrp(Float.parseFloat(record.getString("mrp")));
			productdata.setRate(Float.parseFloat(record.getString("rate")));
			productdata.setExpiredate(formatter1.parse(record.getString("exp")));
			productdata.setCompany(record.getString("company"));
			productdata.setSupplier(record.getString("supplier"));
			ListProduct.add(productdata);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		
		productrepository.saveAll(ListProduct);
		return "Upload Success";
	}

	@Override
	public List<String> findBySupplierName(String SupplierName) throws ProductNotFoundException {
		
		List<String> ProductList= new ArrayList<>();
		try {
		String q1 = "SELECT distinct(name) FROM ProductData where Supplier = '"+SupplierName+"' and stock <> 0";
		
		Query query = entityManager.createQuery(q1);
		
		ProductList= query.getResultList();
		
		for(String pd:ProductList) {
			System.out.println("Product Code : "+pd);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ProductNotFoundException(e.getMessage());
		}
		return ProductList;
	}

	@Override
	public List<String> findByProductName(String ProductName) throws ProductNotFoundException {
		
		List<String> SupplierList=new ArrayList<>();
		try {
		String q1 = "SELECT distinct(supplier) FROM ProductData where name = '"+ProductName+"' and stock <> 0";
		
		Query query = entityManager.createQuery(q1);
		
		SupplierList= query.getResultList();
		
		for(String sl:SupplierList) {
			System.out.println("Product name  : "+sl);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ProductNotFoundException(e.getMessage());
		}
		return SupplierList;
	}

	@Override
	public List<String> ExpireCheckBySupplierName(String SupplierName,int PageNo,int PageSize) throws ProductNotFoundException {
		
		int fromIndex=0;
		try {
		long millis=System.currentTimeMillis();  
	    java.sql.Date date = new java.sql.Date(millis);       
		System.out.println(date); 
		List<String> unexpireProductList= new ArrayList<>();
		
		String q1 = "SELECT distinct(name) FROM ProductData where Supplier = '"+SupplierName+"' and expiredate >= '"+date+"' and stock <> 0";
		
		Query query = entityManager.createQuery(q1);
		 
		 unexpireProductList= query.getResultList();
		 
		 fromIndex= (PageNo-1)*PageSize;
		 if (PageSize<=0 || PageNo<=0||unexpireProductList.size()<=fromIndex||unexpireProductList==null){
				return Collections.emptyList();
			}
		
		for(String upl:unexpireProductList) {
			System.out.println("Product name : "+upl);
		}
		
		return unexpireProductList.subList(fromIndex, Math.min(fromIndex+PageSize, unexpireProductList.size()));
		}catch(Exception e) {
			e.printStackTrace();
			throw new ProductNotFoundException(e.getMessage());
		}
	}
}
