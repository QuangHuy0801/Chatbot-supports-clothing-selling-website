package WebProject.WebProject.service;

import java.util.List;

import WebProject.WebProject.entity.Conversations;

public interface ConversationsService {
	List<Conversations> getAllConversations(String user_id);
	Conversations saveConversations(Conversations conversations);
}
