package metaint.replanet.rest.chatbot.repository;

import metaint.replanet.rest.chatbot.entity.Chatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatbotRepository extends JpaRepository<Chatbot, Integer> {

}
