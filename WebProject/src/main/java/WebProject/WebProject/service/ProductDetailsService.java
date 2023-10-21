package WebProject.WebProject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import WebProject.WebProject.entity.Product;
import WebProject.WebProject.entity.Product_details;

public interface ProductDetailsService {
	Product_details getProduct_detailsById(int id);
	
	/*
	 * Product_details findByProduct_details_size_color(int product_id, String
	 * color, int size);
	 */
	
	Product_details findByProduct_details_size_color(int product_id,int size, String color);
	
	Product_details saveProduct_details(Product_details product_details);
	
	Product_details updateProduct_details(Product_details product_details);
	
	List<String> renderColorByIdProduct(int product_id);
	
	List<String> renderSizeByIdProduct(int product_id);
	
	void updateQuatityByIdProduct(int product_id);
	
	}
