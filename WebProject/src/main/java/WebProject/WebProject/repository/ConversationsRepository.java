package WebProject.WebProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.twilio.rest.conversations.v1.Conversation;

import WebProject.WebProject.entity.Conversations;
import WebProject.WebProject.entity.Order;
import WebProject.WebProject.entity.Product;
@Repository
public interface ConversationsRepository extends JpaRepository<Conversations,Integer> {
	@Query(value="SELECT * FROM conversations c WHERE c.user_id = ?1 ORDER BY c.timestamp ASC" ,nativeQuery = true)
	List<Conversations> getAllConversations(String id_user);
	
}
