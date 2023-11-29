package metaint.replanet.rest.chatbot.repository;

import metaint.replanet.rest.chatbot.dto.ChatbotDTO;
import metaint.replanet.rest.chatbot.entity.Chatbot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ChatbotRepositoryTest {


    private ChatbotRepository chatbotRepository;

    @DisplayName("테이블 전체 조회 테스트")
    @Test
    public void testSelectAllQuestion() {
        //when
        List<Chatbot> foundChatbotList = chatbotRepository.findAll();
        //then
        Assertions.assertNotNull(foundChatbotList);
        foundChatbotList.forEach(System.out::println);
    }

    @DisplayName("답변 단건 조회 테스트")
    @Test
    public void testSelectOneAnswer() {
        //given
        int questionCode = 2;
        //when
        Optional<Chatbot> foundAnswer = chatbotRepository.findById(questionCode);

        //then
        Assertions.assertNotNull(foundAnswer);
        System.out.println(foundAnswer);
    }

}
