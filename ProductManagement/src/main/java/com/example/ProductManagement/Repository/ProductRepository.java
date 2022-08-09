package com.example.ProductManagement.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ProductManagement.model.ProductData;

public interface ProductRepository extends JpaRepository<ProductData,Long> {

	/*@Query(value = "CALL Find_By_SuPPLIER_NAME(:in_supplier_name );", nativeQuery = true)
	public List<ProductData> FindBySupplierId(@Param("In_Supplier_name") String in_supplier_name );
	*/
}
