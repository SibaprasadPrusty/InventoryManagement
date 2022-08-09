package com.example.ProductManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.ProductManagement.Service.ProductDataService;

@RestController
public class ProductController {

	@Autowired
	private ProductDataService productdataservice;
	
	//http://localhost:8080/upload?File
	@PostMapping("/upload")
	public String Upload(@RequestParam("File") MultipartFile File) throws Exception {
		
		return productdataservice.insertData(File);
	}
	
	//http://localhost:8080/Supplier/V.H.M.TRADERS VIJAYAWADA
	@GetMapping("/Supplier/{name}")
	public List<String> findBySupplierName(@PathVariable("name") String SupplierName)
	{
		return productdataservice.findBySupplierName(SupplierName);
	}
	
	//http://localhost:8080/Product/BD EMERALD SYRINGE
	@GetMapping("/Product/{name}")
	public List<String> findByProductName(@PathVariable("name") String ProductName)
	{
		return productdataservice.findByProductName(ProductName);
	}
	
	//http://localhost:8080/ExpireDateCheck/MACLEODS PHARMACEUTICALS LTD  HYDERABAD   TEL/2/5
	@GetMapping("/ExpireDateCheck/{name}/{PageNo}/{PageSize}")
	public List<String> ExpireCheckBySupplierName(@PathVariable("name") String SupplierName,@PathVariable("PageNo") int PageNo,@PathVariable("PageSize") int PageSize)
	{
		return productdataservice.ExpireCheckBySupplierName(SupplierName,PageNo,PageSize);
	}
}
