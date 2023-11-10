package WebProject.WebProject.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WebProject.WebProject.entity.Product;
import WebProject.WebProject.entity.Product_details;
import WebProject.WebProject.repository.ProductDetailsReponsitory;
import WebProject.WebProject.repository.ProductRepository;
import WebProject.WebProject.service.ProductDetailsService;

@Service
public class ProductDetailsServicempl implements ProductDetailsService {

	@Autowired
	ProductDetailsReponsitory productDetailsReponsitory;

	@Override
	public Product_details getProduct_detailsById(int id) {
		// TODO Auto-generated method stub
		return productDetailsReponsitory.findById(id);
	}
	
	/*
	 * @Override public Product_details findByProduct_details_size_color(int
	 * product_id, String color, int size) { // TODO Auto-generated method stub
	 * return productDetailsReponsitory.findByProduct_details_size_color(
	 * product_id, color, size); }
	 */
	@Override
	public Product_details findByProduct_details_size_color(int product_id, int size, int color) {
		// TODO Auto-generated method stub
		return productDetailsReponsitory.findByProduct_details_size_color( product_id, size ,color);
	}

	
	@Override
	public Product_details saveProduct_details(Product_details product_details) {
		// TODO Auto-generated method stub
		return productDetailsReponsitory.save(product_details);
	}
	
	@Override
	public Product_details updateProduct_details(Product_details product_details) {
		// TODO Auto-generated method stub
		return productDetailsReponsitory.save(product_details);
	}
	
	@Override
	public List<String> renderColorByIdProduct(int product_id) {
		// TODO Auto-generated method stub
		return productDetailsReponsitory.renderColorByProductId(product_id);
	}
	
	@Override
	public List<String> renderSizeByIdProduct(int product_id) {
		// TODO Auto-generated method stub
		return productDetailsReponsitory.renderSizeByProductId(product_id);
	}
	
	@Override
	public void updateQuatityByIdProduct(int product_id) {
		productDetailsReponsitory.updateQuantityByProductId(product_id);
		return;
	}
	
	
}
