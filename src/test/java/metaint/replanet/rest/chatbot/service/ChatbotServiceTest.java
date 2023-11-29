package metaint.replanet.rest.chatbot.service;

import metaint.replanet.rest.chatbot.dto.ChatbotDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ChatbotServiceTest {

    @Autowired
    private ChatbotService chatbotService;

    @DisplayName("챗봇 질문 리스트 조회 테스트")
    @Test
    public void testSelectAllQuestion() {
        //when
        List<ChatbotDTO> questionList = chatbotService.selectAllQuestion();
        //then
        Assertions.assertNotNull(questionList);
        questionList.forEach(System.out::println);
    }

    @DisplayName("챗봇 단건 조회 테스트")
    @Test
    public void testSelectOneAnswer() {
        //given
        int questionCode = 1;
        //when
        ChatbotDTO oneAnswer = chatbotService.selectOneAnswer(questionCode);
        //then
        Assertions.assertNotNull(oneAnswer);
        System.out.println(oneAnswer);
    }



}
