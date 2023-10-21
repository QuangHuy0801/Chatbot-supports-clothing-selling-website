package WebProject.WebProject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WebProject.WebProject.entity.Conversations;
import WebProject.WebProject.entity.Product_details;
import WebProject.WebProject.repository.ConversationsRepository;
import WebProject.WebProject.service.ConversationsService;

@Service
public class ConversationsServiceImpl implements ConversationsService{
		@Autowired
		 ConversationsRepository conversationsRepository;
		@Override
		public List< Conversations > getAllConversations(String user_id) {
			// TODO Auto-generated method stub
			return conversationsRepository.getAllConversations(user_id);
		}
		@Override
		public Conversations saveConversations(Conversations conversations) {
			// TODO Auto-generated method stub
			return conversationsRepository.save(conversations);
		}
	}

