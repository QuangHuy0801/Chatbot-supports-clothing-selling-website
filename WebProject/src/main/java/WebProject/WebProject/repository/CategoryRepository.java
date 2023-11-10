package WebProject.WebProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import WebProject.WebProject.entity.Category;
import WebProject.WebProject.entity.Conversations;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	Category getById(int id);
	
	@Query(value="SELECT c.id FROM category c WHERE c.category_name=?1" ,nativeQuery = true)
	String getIdByName(String name);
	

}
