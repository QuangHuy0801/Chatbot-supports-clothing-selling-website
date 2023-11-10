package WebProject.WebProject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import WebProject.WebProject.entity.Product;
import WebProject.WebProject.entity.Product_details;
@Repository
public interface ProductDetailsReponsitory  extends JpaRepository<Product_details,Long> {
	
	Product_details findById(int id);
	
	/*
	 * @Query(
	 * value="select * from product_details pd where pd.product_id = ?1 and pd.color = ?2 and pd.size = ?3"
	 * ,nativeQuery = true) Product_details findByProduct_details_size_color(int
	 * product_id, String color, int size);
	 */
	
	@Query(value="select * from product_details pd where pd.product_id = ?1  and pd.size_id = ?2  and pd.color_id = ?3",nativeQuery = true)
	Product_details findByProduct_details_size_color(int product_id,  int size,int color);
	
	
	@Query(value="select distinct color from product_details pd where pd.product_id = ?1  ORDER BY pd.color DESC",nativeQuery = true)
	List<String> renderColorByProductId(int product_id);
	
	@Query(value="select distinct color from product_details pd ORDER BY pd.color DESC",nativeQuery = true)
	List<String> renderColorAll();
	@Query(value="select distinct size from product_details pd  ORDER BY pd.size ASC",nativeQuery = true)
	List<String> renderSizeAll();
	
	@Query(value="select distinct size from product_details pd where pd.product_id = ?1  ORDER BY pd.size ASC",nativeQuery = true)
	List<String> renderSizeByProductId(int product_id);
	
	@Query(value="select * from product_details pd where pd.product_id=?1",nativeQuery = true)
	List<Product_details> findProduct_detailsByIdproduct( int idProduct);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE product p SET p.quantity = (SELECT SUM(pd.quantity) FROM product_details pd WHERE pd.product_id = ?1) WHERE p.id = ?1", nativeQuery = true)
	void updateQuantityByProductId(int product_id);

}
