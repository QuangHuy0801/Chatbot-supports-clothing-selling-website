package WebProject.WebProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import WebProject.WebProject.entity.Size;

@Repository
public interface SizeReponsitory  extends JpaRepository < Size,Long> {
	@Query(value="SELECT str_size FROM size ORDER BY id ASC ",nativeQuery = true)
	List<String> getAllSize( );
	
	@Query(value="SELECT str_size FROM size s WHERE s.id IN ( SELECT DISTINCT pd.size_id FROM product_details pd WHERE pd.product_id = ?1)ORDER BY s.id ASC",nativeQuery = true)
	List<String> getColorByIdProduct(int product_id );
	
	@Query(value="SELECT id FROM size s WHERE s.str_size=?1",nativeQuery = true)
	int getIdByName( String name);
	
	@Query(value="SELECT * FROM size s WHERE s.str_size=?1",nativeQuery = true)
	Size getByName( String name);
}
