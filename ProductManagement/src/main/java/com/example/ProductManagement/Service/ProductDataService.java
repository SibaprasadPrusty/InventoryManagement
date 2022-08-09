package com.example.ProductManagement.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.ProductManagement.Exception.ProductNotFoundException;


public interface ProductDataService {
	public String insertData(MultipartFile File) throws IOException, Exception;
	public List<String> findBySupplierName(String SupplierName) throws ProductNotFoundException ;
	public List<String> findByProductName(String ProductName) throws ProductNotFoundException ;
	public List<String> ExpireCheckBySupplierName(String SupplierName,int PageNo,int PageSize) throws ProductNotFoundException ;
}
