package WebProject.WebProject.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import WebProject.WebProject.entity.Category;
import WebProject.WebProject.entity.Conversations;
import WebProject.WebProject.entity.Product;
import WebProject.WebProject.entity.User;
import WebProject.WebProject.repository.ColorReponsitory;
import WebProject.WebProject.repository.ConversationsRepository;
import WebProject.WebProject.repository.ProductDetailsReponsitory;
import WebProject.WebProject.repository.ProductRepository;
import WebProject.WebProject.repository.SizeReponsitory;
import WebProject.WebProject.service.CategoryService;
import WebProject.WebProject.service.ConversationsService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ConversationsRepository conversationsRepository;
	@Autowired
	ConversationsService conversationsService;
	@Autowired
	ProductDetailsReponsitory productDetailsReponsitory;
	
	@Autowired
	ColorReponsitory colorReponsitory;
	
	@Autowired
	SizeReponsitory sizeReponsitory;

	@Autowired
	HttpSession session;

	@GetMapping("/category/{id}")
	public String category(@PathVariable int id, Model model) throws Exception {
		Pageable pageable = PageRequest.of(0, 12);
		Page<Product> page = productRepository.findAllByCategory_id(id, pageable);
		Category category = categoryService.getCategoryById(id);
		List<Category> listCategory = categoryService.findAll();
		List<Product> listProduct = null;
		if (category != null) {
			listProduct = category.getProduct();
		}
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
		int TotalPro = listProduct.size();
		model.addAttribute("TotalPro",TotalPro);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("search_input", null);
		model.addAttribute("listProduct", page);
		model.addAttribute("idCate", id);
		model.addAttribute("noPageable", "category");
		model.addAttribute("renderColorAll", colorReponsitory.getAllColor());
		model.addAttribute("renderSizeAll", sizeReponsitory.getAllSize());
		return "shop";
	}
	
	@GetMapping("/category/{id}/{p}")
	public String categoryPage(@PathVariable int id,@PathVariable int p, Model model, HttpServletRequest request) throws Exception {
		String referer = request.getHeader("Referer");
		Pageable pageable = PageRequest.of(p, 12);
		Page<Product> page = productRepository.findAllByCategory_id(id, pageable);
		Category category = categoryService.getCategoryById(id);
		List<Category> listCategory = categoryService.findAll();
		List<Product> listProduct = null;
		if (category != null) {
			listProduct = category.getProduct();
		}
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
		int TotalPro = listProduct.size();
		model.addAttribute("TotalPro",TotalPro);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("search_input", null);
		model.addAttribute("listProduct", page);
		model.addAttribute("referer", referer);
		model.addAttribute("idCate", id);
//		model.addAttribute("noPageable", "noPageable");
		model.addAttribute("noPageable", "category");
		model.addAttribute("renderColorAll", colorReponsitory.getAllColor());
		model.addAttribute("renderSizeAll", sizeReponsitory.getAllSize());
		return "shop";
	}
}
