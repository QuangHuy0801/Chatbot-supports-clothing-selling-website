package WebProject.WebProject.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import WebProject.WebProject.entity.Cart;
import WebProject.WebProject.entity.Category;
import WebProject.WebProject.entity.Conversations;
import WebProject.WebProject.entity.Product;
import WebProject.WebProject.entity.User;
import WebProject.WebProject.repository.ConversationsRepository;
import WebProject.WebProject.repository.ProductRepository;
import WebProject.WebProject.service.CartService;
import WebProject.WebProject.service.CategoryService;
import WebProject.WebProject.service.ConversationsService;
import WebProject.WebProject.service.CookieService;
import WebProject.WebProject.service.ProductDetailsService;
import WebProject.WebProject.service.ProductService;
import WebProject.WebProject.service.UserService;

@Controller
public class ProductControler {

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;
	@Autowired
	ConversationsRepository conversationsRepository;
	@Autowired
	ConversationsService conversationsService;
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductDetailsService productDetailsService;

	@Autowired
	CartService cartService;

	@Autowired
	HttpSession session;

	@Autowired
	CookieService cookie;

	@GetMapping(value = {"","/home"})
	public String listStudents(Model model) throws Exception {
		Cookie user_name = cookie.read("user_name");
		Cookie remember = cookie.read("remember");
		String error_momo = (String) session.getAttribute("error_momo");
		String NoSignIn = (String) session.getAttribute("NoSignIn");
		session.setAttribute("NoSignIn", null);
		session.setAttribute("error_momo", null);
		String a = (String) session.getAttribute("NoSignIn");
		System.out.println(a);
		System.out.println(NoSignIn);
		User acc = (User) session.getAttribute("acc");
		if (remember != null) {
			acc = userService.findByIdAndRole(user_name.getValue(), "user");
			session.setAttribute("acc", acc);
			List<Cart> listCart = cartService.GetAllCartByUser_id(acc.getId());
			session.setAttribute("countCart", listCart.size());
		}
		if(acc!=null) {
			List<Cart> listCart = cartService.GetAllCartByUser_id(acc.getId());
			session.setAttribute("countCart", listCart.size());
			}
		if (session.getAttribute("acc") == null)
			session.setAttribute("countCart", "0");
		model.addAttribute("error_momo", error_momo);
		model.addAttribute("NoSignIn", NoSignIn);
		
		List<Product> Top12ProductBestSellers = productService.findTop12ProductBestSellers();
		List<Product> Top12ProductNewArrivals = productService.findTop12ProductNewArrivals();
		model.addAttribute("Top12ProductBestSellers", Top12ProductBestSellers);
		model.addAttribute("Top12ProductNewArrivals", Top12ProductNewArrivals);
		//chatbot
		User user = (User) session.getAttribute("acc");
		List <Conversations> conversations = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
	    Timestamp timestamp = Timestamp.valueOf(now);
	    now = LocalDateTime.now();
		if(user!=null) {
		conversations= conversationsRepository.getAllConversations(user.getId());
		if(conversations.isEmpty()) {
			 Conversations conversations_bot = new Conversations();
		        conversations_bot.setMessage("Man-Fashion xin chào, tôi có thể giúp gì cho bạn");
		        conversations_bot.setIs_user(0);
		        conversations_bot.setUser(user);
		        timestamp = Timestamp.valueOf(now);
		    	conversations_bot.setTimestamp(timestamp);
		    	conversations.add(conversations_bot);
		  	conversationsService.saveConversations(conversations_bot);
		}}
		else {
			 Conversations conversations_bot = new Conversations();
		        conversations_bot.setMessage("Man-Fashion xin chào, tôi có thể giúp gì cho bạn");
		        conversations_bot.setIs_user(0);
		        conversations_bot.setUser(user);
		        conversations_bot.setTimestamp(timestamp);
		    	conversations.add(conversations_bot);
		}
		model.addAttribute("listConversations", conversations);
		//endchatbot
		return "index";
	}
	@GetMapping("/shop")
	public String shop(Model model) throws Exception {
		List<Product> lp = productService.getAllProduct();
		int TotalPro = lp.size();
		model.addAttribute("TotalPro",TotalPro);
		Pageable pageable = PageRequest.of(0, 12);
		Page<Product> page = productRepository.findAll(pageable);
		List<Category> listCategory = categoryService.findAll();
		String search_input = (String) session.getAttribute("search_input");
		System.out.println("in");
		//chatbot
		User user = (User) session.getAttribute("acc");
		List <Conversations> conversations = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
	    Timestamp timestamp = Timestamp.valueOf(now);
	    now = LocalDateTime.now();
		if(user!=null) {
		conversations= conversationsRepository.getAllConversations(user.getId());
		if(conversations.isEmpty()) {
			 Conversations conversations_bot = new Conversations();
		        conversations_bot.setMessage("Xin chào bạn đến với Man-Fashion, tôi có thể giúp gì cho bạn");
		        conversations_bot.setIs_user(0);
		        conversations_bot.setUser(user);
		        timestamp = Timestamp.valueOf(now);
		    	conversations_bot.setTimestamp(timestamp);
		    	conversations.add(conversations_bot);
		  	conversationsService.saveConversations(conversations_bot);
		}}
		else {
			 Conversations conversations_bot = new Conversations();
		        conversations_bot.setMessage("Xin chào bạn đến với Man-Fashion, tôi có thể giúp gì cho bạn");
		        conversations_bot.setIs_user(0);
		        conversations_bot.setUser(user);
		        conversations_bot.setTimestamp(timestamp);
		    	conversations.add(conversations_bot);
		}
		model.addAttribute("listConversations", conversations);
		//endchatbot
		model.addAttribute("listProduct", page);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("search_input", search_input);
		return "shop";
	}
	@GetMapping("/shop/{id}")
	public String shopPage(Model model, @PathVariable int id) throws Exception {
		List<Product> lp = productService.getAllProduct();
		int TotalPro = lp.size();
		model.addAttribute("TotalPro",TotalPro);
		Pageable pageable = PageRequest.of(id, 12);
		Page<Product> page = productRepository.findAll(pageable);
		model.addAttribute("listProduct", page);
		List<Category> listCategory = categoryService.findAll();
		String search_input = (String) session.getAttribute("search_input");
		User user = (User) session.getAttribute("acc");
		if (user != null) {
			model.addAttribute("user_Name", user.getUser_Name());
		}
		if (listCategory != null)
			model.addAttribute("listCategory", listCategory);
		else
			model.addAttribute("listCategory", null);
		model.addAttribute("search_input", search_input);
		//chatbot
				
				List <Conversations> conversations = new ArrayList<>();
				LocalDateTime now = LocalDateTime.now();
			    Timestamp timestamp = Timestamp.valueOf(now);
			    now = LocalDateTime.now();
				if(user!=null) {
				conversations= conversationsRepository.getAllConversations(user.getId());
				if(conversations.isEmpty()) {
					 Conversations conversations_bot = new Conversations();
				        conversations_bot.setMessage("Man-Fashion xin chào, tôi có thể giúp gì cho bạn");
				        conversations_bot.setIs_user(0);
				        conversations_bot.setUser(user);
				        timestamp = Timestamp.valueOf(now);
				    	conversations_bot.setTimestamp(timestamp);
				    	conversations.add(conversations_bot);
				  	conversationsService.saveConversations(conversations_bot);
				}}
				else {
					 Conversations conversations_bot = new Conversations();
				        conversations_bot.setMessage("Man-Fashion xin chào, tôi có thể giúp gì cho bạn");
				        conversations_bot.setIs_user(0);
				        conversations_bot.setUser(user);
				        conversations_bot.setTimestamp(timestamp);
				    	conversations.add(conversations_bot);
				}
				model.addAttribute("listConversations", conversations);
				//endchatbot
		return "shop";
	}

	@GetMapping("/productDetail/{id}")
	public String ProductDetailId(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);
		if(product !=null){
		List<Product> relatedProduct = productService.findTop4ProductByCategory_id(product.getCategory().getId());
		model.addAttribute("relatedProduct", relatedProduct);
		model.addAttribute(product);
		model.addAttribute("renderColor",productDetailsService.renderColorByIdProduct(id));
		model.addAttribute("renderSize",productDetailsService.renderSizeByIdProduct(id));
		System.out.println(productDetailsService.renderColorByIdProduct(id));
		System.out.println(productDetailsService.renderSizeByIdProduct(id));
		
		return "shop-details";
		}
		else {
			return "redirect:/home";
		}
		
	}

//	@GetMapping("/productDetail")
//	public String ProductDetail(Model model) {
//		Product product = (Product) session.getAttribute("product");
//		
//	}

	@PostMapping("/search")
	public String Search(@ModelAttribute("search-input") String search_input, Model model) throws Exception {
		session.setAttribute("search_input", search_input);
		return "redirect:/search/0";
	}
	@GetMapping("/search/{id}")
	public String SearchPage(@PathVariable int id, Model model) throws Exception {
		List<Category> listCategory = categoryService.findAll();
		String search_input = (String) session.getAttribute("search_input");
		if (search_input != null) {
			Pageable pageable = PageRequest.of(id, 12);
			System.out.println(search_input);
			Page<Product> listProduct = productRepository.findByProduct_NameContaining(search_input, pageable);
			List<Product> listProductAll = productRepository.findByProduct_NameContaining(search_input);
			System.out.println(listProduct);
			int TotalPro = listProductAll.size();
			model.addAttribute("TotalPro",TotalPro);
			model.addAttribute("search_input", search_input);
			model.addAttribute("listProduct", listProduct);
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("pageSearch", "pageSearch");
			model.addAttribute("noPageable", "search");
			for(Product y :listProduct) {
				System.out.println(y);
			}
			return "shop";
		} else {
			model.addAttribute("TotalPro",0);
			model.addAttribute("noPageable", "search");
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("search_input", null);
			model.addAttribute("listProduct", null);
			return "shop";
		}
	}
	
	@GetMapping("blog-details")
	public String blogDetailsView(Model model) {
		return "blog-details";
	}
}
