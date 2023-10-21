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
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import WebProject.WebProject.entity.Conversations;
import WebProject.WebProject.entity.User;
import WebProject.WebProject.service.ConversationsService;

@RestController
@RequestMapping("/api/chat")
public class ChatbotController {
	@Autowired
	ConversationsService conversationsService;
	@Autowired
	HttpSession session;

	@PostMapping
	public String sendMessageToChatbot(@RequestBody String message) {
		User user = (User) session.getAttribute("acc");
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
		String response = sendRequestToChatbot(message);
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
