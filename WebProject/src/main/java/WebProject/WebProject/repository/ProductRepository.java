package WebProject.WebProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import WebProject.WebProject.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	@Query(value="select * from product p where p.product_name like %?1%",nativeQuery = true)
	List<Product> findByProduct_NameContaining(String name);
	
	@Query(value="select * from product p where p.id = ?1",nativeQuery = true)
	Product findByProduct_byId(int id);
	
	@Query(value="Select * From product p ORDER BY p.quantity DESC LIMIT 12;",nativeQuery = true)
	List<Product> findTop12ProductBestSellers();
	
	@Query(value="Select * From product p ORDER BY p.created_at DESC LIMIT 12;",nativeQuery = true)
	List<Product> findTop12ProductNewArrivals();
	
	Page<Product> findAllByCategory_id(int id, Pageable pageable);
	
	Product findById(int id);
	
	@Query(value="select * from `fashionstore`.product where `fashionstore`.product.product_name like %?1% and `fashionstore`.product.category_id= ?2",nativeQuery = true)
	Page<Product> findByProduct_NameAndCategory_idContaining(String name, int category_id, Pageable pageable);
	
	@Query(value="select * from `fashionstore`.product where `fashionstore`.product.product_name like %?1%",nativeQuery = true)
	Page<Product> findByProduct_NameContaining(String name, Pageable pageable);
	
	@Query(value="select * from product p where p.category_id = ?1 ORDER BY p.sold DESC LIMIT 4;",nativeQuery = true)
	List<Product> findTop4ProductByCategory_id(int id);
	
	@Query(value="SELECT * FROM product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM product_details pd WHERE pd.size_id = ?1 AND pd.color_id = ?2)",nativeQuery = true)
	Page<Product> findByProduct_size_color_all( int size,int color, Pageable pageable);
	
	@Query(value="SELECT * FROM product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM product_details pd WHERE pd.size_id = ?1 AND pd.color_id = ?2)",nativeQuery = true)
	List<Product> findByProduct_size_color_all( int size,int color);
	
	@Query(value="SELECT * FROM product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM product_details pd WHERE   pd.color_id = ?1)",nativeQuery = true)
	Page<Product> findByProduct_color_all( int color, Pageable pageable);
	
	@Query(value="SELECT * FROM product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM product_details pd WHERE  pd.color_id = ?1)",nativeQuery = true)
	List<Product> findByProduct_color_all(int color);
	
	@Query(value="SELECT * FROM `fashionstore`.product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM `fashionstore`.product_details pd WHERE pd.size_id = ?1)",nativeQuery = true)
	Page<Product> findByProduct_size_all( int size, Pageable pageable);
	
	@Query(value="SELECT * FROM `fashionstore`.product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM `fashionstore`.product_details pd WHERE pd.size_id = ?1)",nativeQuery = true)
	List<Product> findByProduct_size_all( int size);
	
	@Query(value="SELECT * FROM `fashionstore`.product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM `fashionstore`.product_details pd WHERE pd.color_id = ?2)and  p.category_id=?1",nativeQuery = true)
	Page<Product> findByProduct_category_color( int category,int color, Pageable pageable);
	
	@Query(value="SELECT * FROM `fashionstore`.product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM `fashionstore`.product_details pd WHERE pd.color_id = ?2) and  p.category_id=?1",nativeQuery = true)
	List<Product> findByProduct_category_color( int category,int color);
	
	@Query(value="SELECT * FROM `fashionstore`.product p WHERE p.price>=?1 and price<=?2 order by p.price asc",nativeQuery = true)
	Page<Product> findByProduct_price( int lowest_Price,int hightest_price, Pageable pageable);
	
	@Query(value="SELECT * FROM `fashionstore`.product p WHERE p.price>=?1 and price<=?2 order by p.price asc",nativeQuery = true)
	List<Product> findByProduct_price( int lowest_Price,int hightest_price);
	
	@Query(value="SELECT * FROM `fashionstore`.product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM `fashionstore`.product_details pd WHERE pd.size_id = ?2)and  p.category_id=?1",nativeQuery = true)
	Page<Product> findByProduct_category_size( int category,int size, Pageable pageable);
	
	@Query(value="SELECT * FROM `fashionstore`.product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM `fashionstore`.product_details pd WHERE pd.size_id = ?2) and  p.category_id=?1",nativeQuery = true)
	List<Product> findByProduct_category_size( int category,int size);
	
	@Query(value="SELECT * FROM `fashionstore`.product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM `fashionstore`.product_details pd WHERE pd.size_id = ?3 and pd.color_id = ?2) and  p.category_id=?1",nativeQuery = true)
	List<Product> findByProduct_category_color_size( int category,int color,int size);
	
	@Query(value="SELECT * FROM `fashionstore`.product p WHERE p.id IN ( SELECT DISTINCT pd.product_id FROM `fashionstore`.product_details pd WHERE pd.size_id = ?3 and pd.color_id = ?2)and  p.category_id=?1",nativeQuery = true)
	Page<Product> findByProduct_category_color_size( int category,int color,int size, Pageable pageable);
	

	
	/*
	 * @Query(value="?1",nativeQuery = true) Page<Product> SQLPageCustom( String
	 * sqlString, Pageable pageable);
	 * 
	 * @Query(value="?1",nativeQuery = true) List<Product> SQLProductCustom( String
	 * sqlString);
	 */
	
}
