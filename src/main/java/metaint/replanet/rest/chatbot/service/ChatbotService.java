package metaint.replanet.rest.chatbot.service;

import metaint.replanet.rest.chatbot.dto.ChatbotDTO;
import metaint.replanet.rest.chatbot.entity.Chatbot;
import metaint.replanet.rest.chatbot.repository.ChatbotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatbotService {

    private ChatbotRepository chatbotRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ChatbotService(ChatbotRepository chatbotRepository, ModelMapper modelMapper) {
        this.chatbotRepository = chatbotRepository;
        this.modelMapper = modelMapper;
    }

    // 챗봇 질문 리스트 조회
    public List<ChatbotDTO> selectAllQuestion() {
        List<Chatbot> questionList = chatbotRepository.findAll();

        questionList.forEach(System.out::println);

        return questionList.stream()
                .map(question -> modelMapper.map(question, ChatbotDTO.class))
                .collect(Collectors.toList());
    }

    // 챗봇 단건 조회
    public ChatbotDTO selectOneAnswer(int questionCode) {
        Chatbot answerResultOne = chatbotRepository.findById(questionCode).orElseThrow(IllegalArgumentException::new);

        ChatbotDTO chatbotDTO = modelMapper.map(answerResultOne, ChatbotDTO.class);

        return chatbotDTO;
    }
}
