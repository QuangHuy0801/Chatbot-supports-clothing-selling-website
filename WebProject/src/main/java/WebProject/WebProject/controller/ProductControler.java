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
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;

import WebProject.WebProject.entity.Cart;
import WebProject.WebProject.entity.Category;
import WebProject.WebProject.entity.Conversations;
import WebProject.WebProject.entity.Product;
import WebProject.WebProject.entity.Product_details;
import WebProject.WebProject.entity.User;
import WebProject.WebProject.repository.ColorReponsitory;
import WebProject.WebProject.repository.ConversationsRepository;
import WebProject.WebProject.repository.ProductDetailsReponsitory;
import WebProject.WebProject.repository.ProductRepository;
import WebProject.WebProject.repository.SizeReponsitory;
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
	ProductDetailsReponsitory productDetailsReponsitory;
	@Autowired
	ConversationsService conversationsService;
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ColorReponsitory colorReponsitory;
	
	@Autowired
	SizeReponsitory sizeReponsitory;


	@Autowired
	ProductDetailsService productDetailsService;

	@Autowired
	CartService cartService;

	@Autowired
	HttpSession session;

	@Autowired
	CookieService cookie;

	@GetMapping(value = { "", "/home" })
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
		if (acc != null) {
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
		// chatbot
		User user = (User) session.getAttribute("acc");
		List<Conversations> conversations = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		now = LocalDateTime.now();
		if (user != null) {
			conversations = conversationsRepository.getAllConversations(user.getId());
			if (conversations.isEmpty()) {
				Conversations conversations_bot = new Conversations();
				conversations_bot.setMessage("Male-Fashion xin chào, tôi có thể giúp gì cho bạn");
				conversations_bot.setIs_user(0);
				conversations_bot.setUser(user);
				timestamp = Timestamp.valueOf(now);
				conversations_bot.setTimestamp(timestamp);
				conversations.add(conversations_bot);
				conversationsService.saveConversations(conversations_bot);
			}
		} else {
			Conversations conversations_bot = new Conversations();
			conversations_bot.setMessage("Male-Fashion xin chào, tôi có thể giúp gì cho bạn");
			conversations_bot.setIs_user(0);
			conversations_bot.setUser(user);
			conversations_bot.setTimestamp(timestamp);
			conversations.add(conversations_bot);
		}
		model.addAttribute("listConversations", conversations);
		session.setAttribute("openBot", 0);
		// endchatbot
		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model) throws Exception {
		List<Product> lp = productService.getAllProduct();
		int TotalPro = lp.size();
		model.addAttribute("TotalPro", TotalPro);
		Pageable pageable = PageRequest.of(0, 12);
		Page<Product> page = productRepository.findAll(pageable);
		List<Category> listCategory = categoryService.findAll();
		String search_input = (String) session.getAttribute("search_input");
		// chatbot
		User user = (User) session.getAttribute("acc");
		List<Conversations> conversations = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		now = LocalDateTime.now();
		if (user != null) {
			conversations = conversationsRepository.getAllConversations(user.getId());

			if (conversations.isEmpty()) {
				Conversations conversations_bot = new Conversations();
				conversations_bot.setMessage("Xin chào bạn đến với Male-Fashion, tôi có thể giúp gì cho bạn");
				conversations_bot.setIs_user(0);
				conversations_bot.setUser(user);
				timestamp = Timestamp.valueOf(now);
				conversations_bot.setTimestamp(timestamp);
				conversations.add(conversations_bot);
				conversationsService.saveConversations(conversations_bot);
			}
		} else {
			Conversations conversations_bot = new Conversations();
			conversations_bot.setMessage("Xin chào bạn đến với Male-Fashion, tôi có thể giúp gì cho bạn");
			conversations_bot.setIs_user(0);
			conversations_bot.setUser(user);
			conversations_bot.setTimestamp(timestamp);
			conversations.add(conversations_bot);
		}
		model.addAttribute("listConversations", conversations);
		// endchatbot
		model.addAttribute("listProduct", page);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("search_input", search_input);
		model.addAttribute("renderColorAll", colorReponsitory.getAllColor());
		model.addAttribute("renderSizeAll", sizeReponsitory.getAllSize());
		session.setAttribute("openBot", 1);
		return "shop";
	}

	@GetMapping("/shop/{id}")
	public String shopPage(Model model, @PathVariable int id) throws Exception {
		List<Product> lp = productService.getAllProduct();
		int TotalPro = lp.size();
		model.addAttribute("TotalPro", TotalPro);
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
		// chatbot

		List<Conversations> conversations = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		now = LocalDateTime.now();
		if (user != null) {
			conversations = conversationsRepository.getAllConversations(user.getId());
			if (conversations.isEmpty()) {
				Conversations conversations_bot = new Conversations();
				conversations_bot.setMessage("Male-Fashion xin chào, tôi có thể giúp gì cho bạn");
				conversations_bot.setIs_user(0);
				conversations_bot.setUser(user);
				timestamp = Timestamp.valueOf(now);
				conversations_bot.setTimestamp(timestamp);
				conversations.add(conversations_bot);
				conversationsService.saveConversations(conversations_bot);
			}
		} else {
			Conversations conversations_bot = new Conversations();
			conversations_bot.setMessage("Male-Fashion xin chào, tôi có thể giúp gì cho bạn");
			conversations_bot.setIs_user(0);
			conversations_bot.setUser(user);
			conversations_bot.setTimestamp(timestamp);
			conversations.add(conversations_bot);
		}
		model.addAttribute("listConversations", conversations);
		model.addAttribute("renderColorAll", colorReponsitory.getAllColor());
		model.addAttribute("renderSizeAll", sizeReponsitory.getAllSize());
		// endchatbot
		return "shop";
	}

	@GetMapping("/productDetail/{id}")
	public String ProductDetailId(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);
		if (product != null) {
			List<Product> relatedProduct = productService.findTop4ProductByCategory_id(product.getCategory().getId());
			model.addAttribute("colorList", colorReponsitory.getColorByIdProduct(id));
			model.addAttribute("sizeList", sizeReponsitory.getColorByIdProduct(id));
			model.addAttribute("relatedProduct", relatedProduct);
			model.addAttribute(product);

			return "shop-details";
		} else {
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
			model.addAttribute("TotalPro", TotalPro);
			model.addAttribute("search_input", search_input);
			model.addAttribute("listProduct", listProduct);
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("pageSearch", "pageSearch");
			model.addAttribute("noPageable", "search");
			model.addAttribute("renderColorAll", colorReponsitory.getAllColor());
			model.addAttribute("renderSizeAll", sizeReponsitory.getAllSize());
			for (Product y : listProduct) {
				System.out.println(y);
			}

			return "shop";
		} else {
			model.addAttribute("TotalPro", 0);
			model.addAttribute("noPageable", "search");
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("search_input", null);
			model.addAttribute("listProduct", null);
			model.addAttribute("renderColorAll", colorReponsitory.getAllColor());
			model.addAttribute("renderSizeAll", sizeReponsitory.getAllSize());
			return "shop";
		}
	}

	@GetMapping("blog-details")
	public String blogDetailsView(Model model) {
		return "blog-details";
	}

	@GetMapping("/findColorSize")
	public String FindColorSize(@RequestParam(name = "category", required = false, defaultValue = "0") int category,
			@RequestParam(name = "color", required = false, defaultValue = "0") int color,
			@RequestParam(name = "size", required = false, defaultValue = "") String size, Model model,
			HttpServletRequest request) throws Exception {
		Pageable pageable = PageRequest.of(0, 12);
		List<Category> listCategory = categoryService.findAll();
		List<Product> listProduct = new ArrayList<>();
		Page<Product> page;
		int idSize=0;
		if(!size.equals("")) {
		 idSize=sizeReponsitory.getIdByName(size.trim());
		 System.out.println(idSize);
		}
		//chỉ màu
		if (color!=0 && size.equals("") && category==0) {
			listProduct = productRepository.findByProduct_color_all(color);
			page = productRepository.findByProduct_color_all(color, pageable);
			//chỉ size
		} else if (color==0 && !size.equals("") && category==0) {
			listProduct = productRepository.findByProduct_size_all(idSize);
			page = productRepository.findByProduct_size_all(idSize, pageable);}
		//màu và size
		else if (color!=0 && !size.equals("") && category==0) {
			listProduct = productRepository.findByProduct_size_color_all(idSize, color);
			page = productRepository.findByProduct_size_color_all(idSize, color, pageable);}
			//loại và màu
		else if (color!=0 && size.equals("") && category!=0) {
				listProduct = productRepository.findByProduct_category_color(category, color);
				page = productRepository.findByProduct_category_color(category, color, pageable);
		}
		// loại và size
		else if (color==0 && !size.equals("") && category!=0) {
			listProduct = productRepository.findByProduct_category_size(category, idSize);
			page = productRepository.findByProduct_category_size(category, idSize, pageable);}
		// loại và size và màu
		else if (color!=0 && !size.equals("") && category!=0) {
				listProduct = productRepository.findByProduct_category_color_size(category,color, idSize);
				page = productRepository.findByProduct_category_color_size(category,color, idSize, pageable);}
		else {
			listProduct = productRepository.findByProduct_size_color_all(idSize, color);
			page = productRepository.findByProduct_size_color_all(idSize, color, pageable);
		}
		/* Page<Product> page = Page.of(listProduct, pageable); */
		// chatbot
		User user = (User) session.getAttribute("acc");
		List<Conversations> conversations = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		now = LocalDateTime.now();
		if (user != null) {
			conversations = conversationsRepository.getAllConversations(user.getId());

			if (conversations.isEmpty()) {
				Conversations conversations_bot = new Conversations();
				conversations_bot.setMessage("Xin chào bạn đến với Male-Fashion, tôi có thể giúp gì cho bạn");
				conversations_bot.setIs_user(0);
				conversations_bot.setUser(user);
				timestamp = Timestamp.valueOf(now);
				conversations_bot.setTimestamp(timestamp);
				conversations.add(conversations_bot);
				conversationsService.saveConversations(conversations_bot);
			}
		} else {
			Conversations conversations_bot = new Conversations();
			conversations_bot.setMessage("Xin chào bạn đến với Male-Fashion, tôi có thể giúp gì cho bạn");
			conversations_bot.setIs_user(0);
			conversations_bot.setUser(user);
			conversations_bot.setTimestamp(timestamp);
			conversations.add(conversations_bot);
		}
		model.addAttribute("listConversations", conversations);
		// endchatbot
		int TotalPro = listProduct.size();
		model.addAttribute("TotalPro", TotalPro);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("search_input", null);
		model.addAttribute("listProduct", page);
		model.addAttribute("color", color);
		model.addAttribute("size", String.valueOf(size));
		model.addAttribute("noPageable", "findColorSize");
		model.addAttribute("renderColorAll", colorReponsitory.getAllColor());
		model.addAttribute("renderSizeAll", sizeReponsitory.getAllSize());
		return "shop";
	}

	@GetMapping("/findColorSize/{p}")
	public String FindColorSizeP(
		@RequestParam(name = "category", required = false, defaultValue = "0") int category,
		@RequestParam(name = "color", required = false, defaultValue = "0") int color,@PathVariable int p,
		@RequestParam(name = "size", required = false, defaultValue = "") String size, Model model,
		HttpServletRequest request) throws Exception {
	Pageable pageable = PageRequest.of(p, 12);
	List<Category> listCategory = categoryService.findAll();
	List<Product> listProduct = new ArrayList<>();
	Page<Product> page;
	int idSize=0;
	if(!size.equals("")) {
	 idSize=sizeReponsitory.getIdByName(size.trim());
	 System.out.println(idSize);
	}
	if (color==0) {
		listProduct = productRepository.findByProduct_size_all(idSize);
		page = productRepository.findByProduct_size_all(idSize, pageable);

	} else if (size.equals("")) {
		listProduct = productRepository.findByProduct_color_all(color);
		page = productRepository.findByProduct_color_all(color, pageable);
	} else {
		listProduct = productRepository.findByProduct_size_color_all(idSize, color);
		page = productRepository.findByProduct_size_color_all(idSize, color, pageable);
	}
	/* Page<Product> page = Page.of(listProduct, pageable); */
	// chatbot
	User user = (User) session.getAttribute("acc");
	List<Conversations> conversations = new ArrayList<>();
	LocalDateTime now = LocalDateTime.now();
	Timestamp timestamp = Timestamp.valueOf(now);
	now = LocalDateTime.now();
	if (user != null) {
		conversations = conversationsRepository.getAllConversations(user.getId());

		if (conversations.isEmpty()) {
			Conversations conversations_bot = new Conversations();
			conversations_bot.setMessage("Xin chào bạn đến với Male-Fashion, tôi có thể giúp gì cho bạn");
			conversations_bot.setIs_user(0);
			conversations_bot.setUser(user);
			timestamp = Timestamp.valueOf(now);
			conversations_bot.setTimestamp(timestamp);
			conversations.add(conversations_bot);
			conversationsService.saveConversations(conversations_bot);
		}
	} else {
		Conversations conversations_bot = new Conversations();
		conversations_bot.setMessage("Xin chào bạn đến với Male-Fashion, tôi có thể giúp gì cho bạn");
		conversations_bot.setIs_user(0);
		conversations_bot.setUser(user);
		conversations_bot.setTimestamp(timestamp);
		conversations.add(conversations_bot);
	}
	model.addAttribute("listConversations", conversations);
	// endchatbot
	int TotalPro = listProduct.size();
	model.addAttribute("TotalPro", TotalPro);
	model.addAttribute("listCategory", listCategory);
	model.addAttribute("search_input", null);
	model.addAttribute("listProduct", page);
	model.addAttribute("color", color);
	model.addAttribute("size", String.valueOf(size));
	model.addAttribute("noPageable", "findColorSize");
	model.addAttribute("renderColorAll", colorReponsitory.getAllColor());
	model.addAttribute("renderSizeAll", sizeReponsitory.getAllSize());
	return "shop";
	
	}
	
	@GetMapping("/findByPrice/{p}")
	public String findByPrice(@RequestParam(name = "category", required = false, defaultValue = "0") int category,
			@RequestParam(name = "lowestPrice", required = false, defaultValue = "0") int lowestPrice,@PathVariable int p,
			@RequestParam(name = "hightestPrice", required = false, defaultValue = "999999999") int hightestPrice, Model model,
			HttpServletRequest request) throws Exception {
		Pageable pageable = PageRequest.of(p, 12);
		List<Category> listCategory = categoryService.findAll();
		List<Product> listProduct = new ArrayList<>();
		Page<Product> page;
		listProduct = productRepository.findByProduct_price(lowestPrice,hightestPrice);
		page = productRepository.findByProduct_price(lowestPrice,hightestPrice, pageable);
		User user = (User) session.getAttribute("acc");
		List<Conversations> conversations = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		now = LocalDateTime.now();
		if (user != null) {
			conversations = conversationsRepository.getAllConversations(user.getId());

			if (conversations.isEmpty()) {
				Conversations conversations_bot = new Conversations();
				conversations_bot.setMessage("Xin chào bạn đến với Male-Fashion, tôi có thể giúp gì cho bạn");
				conversations_bot.setIs_user(0);
				conversations_bot.setUser(user);
				timestamp = Timestamp.valueOf(now);
				conversations_bot.setTimestamp(timestamp);
				conversations.add(conversations_bot);
				conversationsService.saveConversations(conversations_bot);
			}
		} else {
			Conversations conversations_bot = new Conversations();
			conversations_bot.setMessage("Xin chào bạn đến với Male-Fashion, tôi có thể giúp gì cho bạn");
			conversations_bot.setIs_user(0);
			conversations_bot.setUser(user);
			conversations_bot.setTimestamp(timestamp);
			conversations.add(conversations_bot);
		}
		model.addAttribute("listConversations", conversations);
		// endchatbot
		int TotalPro = listProduct.size();
		model.addAttribute("TotalPro", TotalPro);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("search_input", null);
		model.addAttribute("listProduct", page);
		
		 model.addAttribute("lowestPrice", lowestPrice);
		 model.addAttribute("hightestPrice",hightestPrice);
		 
		model.addAttribute("noPageable", "findPrice");
		model.addAttribute("renderColorAll", colorReponsitory.getAllColor());
		model.addAttribute("renderSizeAll", sizeReponsitory.getAllSize());
		return "shop";
	}
	
	@GetMapping("/findByPrice")
	public String findByPrice(@RequestParam(name = "category", required = false, defaultValue = "0") int category,
			@RequestParam(name = "lowestPrice", required = false, defaultValue = "0") int lowestPrice,
			@RequestParam(name = "hightestPrice", required = false, defaultValue = "999999999") int hightestPrice, Model model,
			HttpServletRequest request) throws Exception {
		Pageable pageable = PageRequest.of(0, 12);
		List<Category> listCategory = categoryService.findAll();
		List<Product> listProduct = new ArrayList<>();
		Page<Product> page;
		listProduct = productRepository.findByProduct_price(lowestPrice,hightestPrice);
		page = productRepository.findByProduct_price(lowestPrice,hightestPrice, pageable);
		User user = (User) session.getAttribute("acc");
		List<Conversations> conversations = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		now = LocalDateTime.now();
		if (user != null) {
			conversations = conversationsRepository.getAllConversations(user.getId());

			if (conversations.isEmpty()) {
				Conversations conversations_bot = new Conversations();
				conversations_bot.setMessage("Xin chào bạn đến với Male-Fashion, tôi có thể giúp gì cho bạn");
				conversations_bot.setIs_user(0);
				conversations_bot.setUser(user);
				timestamp = Timestamp.valueOf(now);
				conversations_bot.setTimestamp(timestamp);
				conversations.add(conversations_bot);
				conversationsService.saveConversations(conversations_bot);
			}
		} else {
			Conversations conversations_bot = new Conversations();
			conversations_bot.setMessage("Xin chào bạn đến với Male-Fashion, tôi có thể giúp gì cho bạn");
			conversations_bot.setIs_user(0);
			conversations_bot.setUser(user);
			conversations_bot.setTimestamp(timestamp);
			conversations.add(conversations_bot);
		}
		model.addAttribute("listConversations", conversations);
		// endchatbot
		int TotalPro = listProduct.size();
		model.addAttribute("TotalPro", TotalPro);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("search_input", null);
		model.addAttribute("listProduct", page);
		
		 model.addAttribute("lowestPrice", lowestPrice);
		 model.addAttribute("hightestPrice",hightestPrice);
		 
		model.addAttribute("noPageable", "findPrice");
		model.addAttribute("renderColorAll", colorReponsitory.getAllColor());
		model.addAttribute("renderSizeAll", sizeReponsitory.getAllSize());
		return "shop";
	}

	/*
	 * @GetMapping("/findByStringSQL") public String
	 * FindByStringSQL( @ModelAttribute("stringSQL") String stringSQL, Model model,
	 * HttpServletRequest request) throws Exception {
	 * 
	 * Pageable pageable = PageRequest.of(0, 12); List<Category> listCategory =
	 * categoryService.findAll(); List<Product> listProduct = new ArrayList<>();
	 * Page<Product> page ; listProduct =
	 * productRepository.SQLProductCustom(stringSQL); page =
	 * productRepository.SQLPageCustom( stringSQL, pageable); Page<Product> page =
	 * Page.of(listProduct, pageable); //chatbot User user = (User)
	 * session.getAttribute("acc"); List <Conversations> conversations = new
	 * ArrayList<>(); LocalDateTime now = LocalDateTime.now(); Timestamp timestamp =
	 * Timestamp.valueOf(now); now = LocalDateTime.now(); if(user!=null) {
	 * conversations= conversationsRepository.getAllConversations(user.getId());
	 * 
	 * if(conversations.isEmpty()) { Conversations conversations_bot = new
	 * Conversations(); conversations_bot.
	 * setMessage("Xin chào bạn đến với Man-Fashion, tôi có thể giúp gì cho bạn");
	 * conversations_bot.setIs_user(0); conversations_bot.setUser(user); timestamp =
	 * Timestamp.valueOf(now); conversations_bot.setTimestamp(timestamp);
	 * conversations.add(conversations_bot);
	 * conversationsService.saveConversations(conversations_bot); }} else {
	 * Conversations conversations_bot = new Conversations(); conversations_bot.
	 * setMessage("Xin chào bạn đến với Man-Fashion, tôi có thể giúp gì cho bạn");
	 * conversations_bot.setIs_user(0); conversations_bot.setUser(user);
	 * conversations_bot.setTimestamp(timestamp);
	 * conversations.add(conversations_bot); }
	 * model.addAttribute("listConversations", conversations); //endchatbot int
	 * TotalPro = listProduct.size(); model.addAttribute("TotalPro",TotalPro);
	 * model.addAttribute("listCategory", listCategory);
	 * model.addAttribute("search_input", null); model.addAttribute("listProduct",
	 * page); model.addAttribute("stringSQL", stringSQL);
	 * model.addAttribute("noPageable", "findByStringSQL");
	 * model.addAttribute("renderColorAll",productDetailsReponsitory.renderColorAll(
	 * ));
	 * model.addAttribute("renderSizeAll",productDetailsReponsitory.renderSizeAll())
	 * ; return "shop"; }
	 * 
	 * @GetMapping("/findByStringSQL/{p}") public String
	 * FindByStringSQLPage( @ModelAttribute("stringSQL") String
	 * stringSQL,@PathVariable int p, Model model, HttpServletRequest request)
	 * throws Exception {
	 * 
	 * Pageable pageable = PageRequest.of(p, 12); List<Category> listCategory =
	 * categoryService.findAll(); List<Product> listProduct = new ArrayList<>();
	 * Page<Product> page ; listProduct =
	 * productRepository.SQLProductCustom(stringSQL); page =
	 * productRepository.SQLPageCustom( stringSQL, pageable); Page<Product> page =
	 * Page.of(listProduct, pageable); //chatbot User user = (User)
	 * session.getAttribute("acc"); List <Conversations> conversations = new
	 * ArrayList<>(); LocalDateTime now = LocalDateTime.now(); Timestamp timestamp =
	 * Timestamp.valueOf(now); now = LocalDateTime.now(); if(user!=null) {
	 * conversations= conversationsRepository.getAllConversations(user.getId());
	 * 
	 * if(conversations.isEmpty()) { Conversations conversations_bot = new
	 * Conversations(); conversations_bot.
	 * setMessage("Xin chào bạn đến với Man-Fashion, tôi có thể giúp gì cho bạn");
	 * conversations_bot.setIs_user(0); conversations_bot.setUser(user); timestamp =
	 * Timestamp.valueOf(now); conversations_bot.setTimestamp(timestamp);
	 * conversations.add(conversations_bot);
	 * conversationsService.saveConversations(conversations_bot); }} else {
	 * Conversations conversations_bot = new Conversations(); conversations_bot.
	 * setMessage("Xin chào bạn đến với Man-Fashion, tôi có thể giúp gì cho bạn");
	 * conversations_bot.setIs_user(0); conversations_bot.setUser(user);
	 * conversations_bot.setTimestamp(timestamp);
	 * conversations.add(conversations_bot); }
	 * model.addAttribute("listConversations", conversations); //endchatbot int
	 * TotalPro = listProduct.size(); model.addAttribute("TotalPro",TotalPro);
	 * model.addAttribute("listCategory", listCategory);
	 * model.addAttribute("search_input", null); model.addAttribute("listProduct",
	 * page); model.addAttribute("stringSQL", stringSQL);
	 * model.addAttribute("noPageable", "findByStringSQL");
	 * model.addAttribute("renderColorAll",productDetailsReponsitory.renderColorAll(
	 * ));
	 * model.addAttribute("renderSizeAll",productDetailsReponsitory.renderSizeAll())
	 * ; return "shop"; }
	 */

}
