package WebProject.WebProject.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import WebProject.WebProject.entity.Cart;
import WebProject.WebProject.entity.Product;
import WebProject.WebProject.entity.ProductImage;
import WebProject.WebProject.entity.Product_details;
import WebProject.WebProject.entity.User;
import WebProject.WebProject.service.CartService;
import WebProject.WebProject.service.ProductDetailsService;
import WebProject.WebProject.service.ProductService;

@Controller
public class CartController {

	@Autowired
	CartService cartService;

	@Autowired
	ProductService productService;


	@Autowired
	ProductDetailsService productDetailsService;
	
	@Autowired
	HttpSession session;

	@GetMapping("/cart")
	public String CartView(Model model) throws Exception {
		User user = (User) session.getAttribute("acc");
		if (user == null) {
			session.setAttribute("NoSignIn", "Vui lòng đăng nhập trước khi thực hiện thao tác");
			return "redirect:/home";
		} else {
			List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
			int Total = 0;
			for (Cart y : listCart) {
				Total = Total + y.getCount() * y.getProduct_details().getProduct().getPrice();
			}
			if (listCart != null) {
				model.addAttribute("Total", Total);
				model.addAttribute("listCart", listCart);
				session.setAttribute("listCart", listCart);
				session.setAttribute("Total", Total);
			}
			return "shopping-cart";
		}
	}

	@GetMapping("/deleteCart/{id}")
	public String GetDeleteCart(@PathVariable int id, Model model, HttpServletRequest request) throws Exception {
		String referer = request.getHeader("Referer");
		User user = (User) session.getAttribute("acc");
		if (user == null) {
			return "redirect:" +referer;
		} else {
			System.out.println(id);
			cartService.deleteById(id);
			session.setAttribute("CartDelete", id);
			List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
			session.setAttribute("countCart", listCart.size());
			return "redirect:/cart";
		}
	}

	@PostMapping("/updateCart")
	public String UpdateCart(HttpServletRequest request, Model model) throws Exception {
		@SuppressWarnings("unchecked")
		List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
		int i = 0;
		for (Cart o : listCart) {
//			System.out.println("count"+i);
//			String a=(String) model.getAttribute("count" + i);
			String a = request.getParameter("count" + i);
			int count = Integer.parseInt(a);
			System.out.println(count);
			o.setCount(count);
			i++;
		}
		for (Cart o : listCart) {
			cartService.saveCart(o);
		}
		return "redirect:/cart";
	}

	/*
	 * @GetMapping("/addToCart/{id}") public String AddToCart(@PathVariable int id,
	 * Model model, HttpServletRequest request) throws Exception { String referer =
	 * request.getHeader("Referer"); User user = (User) session.getAttribute("acc");
	 * System.out.println("1234"); if (user == null) {
	 * session.setAttribute("AddToCartErr",
	 * "Vui lòng đăng nhập trước khi thực hiện thao tác"); return "redirect:" +
	 * referer; } else { List<Cart> listCart =
	 * cartService.GetAllCartByUser_id(user.getId()); Product product =
	 * productService.getProductById(id); int cart = 0; for (Cart y : listCart) { if
	 * (y.getProduct().getId() == id) { cart++; } } if (cart > 0) { for (Cart y :
	 * listCart) { if (y.getProduct().getId() == id) { y.setCount(y.getCount() + 1);
	 * cartService.saveCart(y); } } } else { Cart newCart = new Cart();
	 * newCart.setCount(1); newCart.setProduct(product); newCart.setUser(user);
	 * cartService.saveCart(newCart); } listCart =
	 * cartService.GetAllCartByUser_id(user.getId());
	 * session.setAttribute("countCart", listCart.size()); return "redirect:" +
	 * referer; } }
	 */

	@PostMapping("/addToCart")
	public String AddToCartPost(@ModelAttribute("product_id") int product_id, @ModelAttribute("count") String a,
			Model model, HttpServletRequest request) throws Exception {
		Product product = productService.getProductById(product_id);
		int count = Integer.parseInt(a);
		String referer = request.getHeader("Referer");
		User user = (User) session.getAttribute("acc");
		String color= request.getParameter("color");
		String size= request.getParameter("size");
		if (user == null) {
			session.setAttribute("AddToCartErr", "Vui lòng đăng nhập trước khi thực hiện thao tác");
			return "redirect:" + referer;
		}
		if(product.getProduct_details().size()!=1) {
		if (color.trim().equals("#") ) {
		    session.setAttribute("AddToCartWanColor", "Vui lòng chọn màu!");
		    return "redirect:" + referer;
		}
		if ( size.trim().equals("1")) {
		    session.setAttribute("AddToCartWanSize", "Vui lòng size !");
		    return "redirect:" + referer;
		}
		
		}
		
			List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
			System.out.println(product_id);
			/*
			 * Product_details product_details =
			 * productDetailsService.getProduct_detailsById(product_id);
			 */
			/* product_details product = productService.getProductById(product_id); */
			/*
			 * Product_details product_details=
			 * productDetailsService.findByProduct_details_size_color(product_id, color,
			 * Integer.parseInt(size));
			 */

			Product_details product_details_size_color= productDetailsService.findByProduct_details_size_color(product_id,Integer.parseInt(size.trim()),color.trim());
			System.out.println(product_details_size_color);
			if(product_details_size_color == null ) {
				session.setAttribute("errcolor_size_color", "màu của size này đã hết!");
			    return "redirect:" + referer;
			}
			int cart = 0;
			  for (Cart y : listCart) { if
			  (y.getProduct_details().getId() == product_details_size_color.getId()) {
			  y.setCount(y.getCount() + count); cartService.saveCart(y); cart++; } } if
			  (cart == 0) { Cart newCart = new Cart(); newCart.setCount(count);
			  newCart.setProduct_details(product_details_size_color) ;
			  newCart.setUser(user);
			  cartService.saveCart(newCart); }
			  
			  listCart = cartService.GetAllCartByUser_id(user.getId());
			  session.setAttribute("countCart", listCart.size());
			 
			
			
			return "redirect:" + referer;
		

	}

}