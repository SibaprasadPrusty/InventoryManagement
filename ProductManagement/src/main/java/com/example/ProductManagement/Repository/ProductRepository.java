package com.example.ProductManagement.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.ProductManagement.model.ProductData;

public interface ProductRepository extends JpaRepository<ProductData,Long> {

	@Query(value="select distinct(SUPPLIER) FROM T_PRODUCT_DATA where NAME = ?1 and STOCK <> 0",nativeQuery= true)
	List<String> findByStockedProduct(String ProductName);
	
	@Query(value="select distinct(NAME) FROM T_PRODUCT_DATA where SUPPLIER = ?1 and STOCK <> 0",nativeQuery= true)
	List<String> findByStockedSupplier(String supplierName);

	@Query(value="select distinct(NAME) FROM T_PRODUCT_DATA where SUPPLIER = ?1 and EXPIREDATE >=?2 and STOCK <> 0",nativeQuery= true)
	List<String> findByExpiredStockSupplier(String supplierName,java.sql.Date date);
}
