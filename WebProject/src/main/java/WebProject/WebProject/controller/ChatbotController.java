package WebProject.WebProject.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import WebProject.WebProject.entity.Conversations;
import WebProject.WebProject.entity.User;
import WebProject.WebProject.repository.CategoryRepository;
import WebProject.WebProject.repository.ColorReponsitory;
import WebProject.WebProject.repository.SizeReponsitory;
import WebProject.WebProject.service.ConversationsService;

@RestController
@RequestMapping("/api/chat")
public class ChatbotController {
	@Autowired
	ConversationsService conversationsService;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	HttpSession session;
	@Autowired
	ColorReponsitory colorReponsitory;
	
	@Autowired
	SizeReponsitory sizeReponsitory;

	@PostMapping
	public String sendMessageToChatbot(@RequestBody String message) {
		User user = (User) session.getAttribute("acc");
		/*
		 * List<String> listFind = (List<String>) session.getAttribute("listUserFind");
		 * if (listFind == null) { listFind = new ArrayList<>(3);
		 * session.setAttribute("listUserFind", listFind); }
		 */
		/* session.removeAttribute("listUserFind"); */
		List<String> listFind =new ArrayList<>();
		 listFind = (List<String>) session.getAttribute("listUserFind");
		if (listFind == null) {
		    listFind = new ArrayList<>(3);
		    listFind.add(null);
		    listFind.add(null);
		    listFind.add(null);
		}

		
	
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		if (user != null) {
			Conversations conversations_user = new Conversations();
			conversations_user.setIs_user(1);
			conversations_user.setMessage(message.substring(1, message.length() - 1));
			conversations_user.setUser(user);
			conversations_user.setTimestamp(timestamp);
			conversationsService.saveConversations(conversations_user);
		}
		// Gửi yêu cầu tới chatbot Python và nhận phản hồi
		String responseFull = sendRequestToChatbot(message);
		String responseSub = responseFull.substring(responseFull.indexOf("-") + 1);
		System.out.println(responseSub);
		String[] words = responseSub.split(" ");
		System.out.println(words);
		for(String w : words) {
		switch ( w ) {
		  case "Áo":
		  case "Quần":
		  case "Giày":
		  case "Mũ":
		  case "Kính":
			  String idCatygory=categoryRepository.getIdByName(w);
			  if(!idCatygory.equals(listFind.get(0))) {
				  listFind.set(2, null);
			  }
				  listFind.set(1, null);
				  System.out.println("ở đây");
				  listFind.set(0,idCatygory);
	        break;
	    case  "blue":
	    case  "red":
	    case  "white":
	    case  "black":
	    case  "yellow":
	    case  "gray":
	    case  "pink":
	    case  "brown":
	    case  "green":
	    	listFind.set(1, w);
	        break;
	    case  "S":
	    case  "L":
	    case  "XL":
	    case  "38":
	    case  "39":
	    case  "40":
	    case  "41":
	    case  "42":
	    case  "43":
	    	listFind.set(2, w);
	        break;
		/*
		 * case "32": case "33": case "34": case "35": case "36": case "37": case "38":
		 * case "39": index=2;
		 */
	    default:
	    	listFind.set(0, null);
	    	listFind.set(1, null);
	    	listFind.set(2, null);
	       
	}}
		System.out.println(listFind);
		String response = responseFull.substring(0, responseFull.indexOf("-"));
		System.out.println(response);
		response=addLinkFindList(listFind,response);
		// Update the session attribute
		session.setAttribute("listUserFind", listFind);

		// Access the element at index 0 only if the list is not empty
		if (!listFind.isEmpty()) {
		    String firstItem = listFind.get(0);
		    System.out.println("First item: " + firstItem);
		}
		session.setAttribute("listUserFind", listFind);
	if (user != null) {
			Conversations conversations_bot = new Conversations();
			conversations_bot.setMessage(response);
			conversations_bot.setIs_user(0);
			conversations_bot.setUser(user);
			now = LocalDateTime.now();
			timestamp = Timestamp.valueOf(now);
			conversations_bot.setTimestamp(timestamp);
			conversationsService.saveConversations(conversations_bot);
		}
		return response;
	}
	
	private String addLinkFindList(List<String> listFind , String response) {
		
		if(listFind.get(0)!=null&&listFind.get(1)==null && listFind.get(2)==null) {
			// chỉ là loại
			if(listFind.get(0).equals("1")||listFind.equals("2")||listFind.equals("3")) {
				response+=" <a href='http://localhost:8081/category/"+listFind.get(0)+"'> clip vào đây </a> <br> Bạn cho tôi biết thêm về màu sắc và kích cỡ để tôi hỗ trợ thêm cho bạn";
			}
		else response+=" <a href='http://localhost:8081/category/"+listFind.get(0)+"'> clip vào đây </a> <br> Bạn cho tôi biết thêm về màu sắc để tôi hỗ trợ thêm cho bạn";}
		// chỉ là màu
		else if (listFind.get(0)==null && listFind.get(1)!=null&& listFind.get(2)==null) {

				response+="  <a href='http://localhost:8081/findColorSize/?color="+String.valueOf(colorReponsitory.getIdColorByName(listFind.get(1)))+"&size='> clip vào đây </a> <br> Bạn cho tôi biết thêm về loại sản phẩm và size mà bạn muốn mua là gì để tôi hỗ trợ thêm cho bạn";}
		// loại và màu
		else if (listFind.get(0)!=null && listFind.get(1)!=null&& listFind.get(2)==null) {
			response+="<a href='http://localhost:8081/findColorSize/?category="+listFind.get(0)+"&color="+String.valueOf(colorReponsitory.getIdColorByName(listFind.get(1)))+"&size='> clip vào đây </a> <br> Bạn cho tôi biết thêm về size để tôi hỗ trợ thêm cho bạn";}	
		// chỉ là size
		else if (listFind.get(0)==null && listFind.get(1)==null&& listFind.get(2)!=null) {
			response+="<a href='http://localhost:8081/findColorSize/?size="+listFind.get(2)+"'> clip vào đây </a> <br> Bạn cho tôi biết thêm về thể loại và màu sắc để tôi hỗ trợ thêm cho bạn";}
		//loại và size
		else if (listFind.get(0)!=null && listFind.get(1)==null&& listFind.get(2)!=null) {
			response+="<a href='http://localhost:8081/findColorSize/?category="+listFind.get(0)+"&size="+listFind.get(2)+"'> clip vào đây </a> <br> Bạn cho tôi biết thêm về màu sắc để tôi hỗ trợ thêm cho bạn";}	
		//loại và size và màu
		else if (listFind.get(0)!=null && listFind.get(1)!=null&& listFind.get(2)!=null) {
			response+="<a href='http://localhost:8081/findColorSize/?category="+listFind.get(0)+"&color="+String.valueOf(colorReponsitory.getIdColorByName(listFind.get(1)))+"&size="+listFind.get(2)+"'> clip vào đây </a> <br>";}	
		//không phải loại ,không phải size, không phải màu
		else response+=".";
		return response;
	}

	private String sendRequestToChatbot(String message) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:5000/chatbot")) // Địa chỉ API
																										// chatbot
																										// Python
				.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(message)).build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return "Error";
		}
	}

}
