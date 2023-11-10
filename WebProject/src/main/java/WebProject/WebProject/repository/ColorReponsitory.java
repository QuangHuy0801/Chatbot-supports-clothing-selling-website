package WebProject.WebProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import WebProject.WebProject.entity.Color;
import WebProject.WebProject.entity.Product;
import WebProject.WebProject.entity.Product_details;
@Repository
public interface ColorReponsitory  extends JpaRepository < Color,Long>  {
	@Query(value="SELECT str_color FROM color  ORDER BY id ASC",nativeQuery = true)
	List<String> getAllColor( );
	
	@Query(value="SELECT str_color FROM color c WHERE c.id IN ( SELECT DISTINCT pd.color_id FROM product_details pd WHERE pd.product_id = ?1)ORDER BY c.id ASC",nativeQuery = true)
	List<String> getColorByIdProduct(int product_id );
	
	@Query(value="SELECT id FROM color c WHERE c.str_color=?1  ORDER BY id DESC",nativeQuery = true)
	int getIdColorByName( String name );
	
	@Query(value="SELECT * FROM color c WHERE c.str_color=?1  ORDER BY id DESC",nativeQuery = true)
	Color getColorByName( String name );

}
