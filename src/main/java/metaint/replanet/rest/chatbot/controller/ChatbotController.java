package metaint.replanet.rest.chatbot.controller;

import io.swagger.annotations.ApiOperation;
import metaint.replanet.rest.chatbot.service.ChatbotService;
import metaint.replanet.rest.common.ResponseMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chatbots")
public class ChatbotController {

    private final ChatbotService chatbotService;

    @Autowired
    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @ApiOperation(value = "FAQ 챗봇 리스트 조회 요청", notes = "현재 등록된 FAQ 챗봇용 질문과 답변 리스트를 조회합니다.", tags = {"FAQ 챗봇 리스트 조회"})
    @GetMapping("/list")
    public ResponseEntity<ResponseMessageDTO> selectChatbotResultList() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("챗봇 리스트 전체 조회", chatbotService.selectAllQuestion());

        ResponseMessageDTO responseMessage = new ResponseMessageDTO(HttpStatus.OK, "조회성공!", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "FAQ 챗봇 단건 조회 요청", notes = "현재 등록된 FAQ 챗봇용 질문과 답변 단건을 질문코드를 조건으로 조회합니다.", tags = {"FAQ 챗봇 단건 조회"})
    @GetMapping("/{questionCode}")
    public ResponseEntity<ResponseMessageDTO> selectChatbotResultOne(@PathVariable int questionCode) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("챗봇 질문코드 단건 조회", chatbotService.selectOneAnswer(questionCode));

        ResponseMessageDTO responseMessage = new ResponseMessageDTO(HttpStatus.OK, "조회성공!", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

}
