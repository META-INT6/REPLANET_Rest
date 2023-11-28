package metaint.replanet.rest.point.service;

import metaint.replanet.rest.auth.entity.Member;
import metaint.replanet.rest.auth.repository.MemberRepository;
import metaint.replanet.rest.point.dto.ExchangeDTO;
import metaint.replanet.rest.point.dto.MemberDTO;
import metaint.replanet.rest.point.dto.PointFileDTO;
import metaint.replanet.rest.point.entity.Exchange;
import metaint.replanet.rest.point.entity.PointFile;
import metaint.replanet.rest.point.repository.ExchangeRepository;
import metaint.replanet.rest.point.repository.PointFileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class pointServiceTest {

    @Autowired
    ExchangeService exchangeService;

    private final ModelMapper modelMapper;

    @Autowired
    public pointServiceTest(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Test
    @DisplayName("포인트 전환 신청 정보 저장 테스트")
    void testInsertExchnage(){
        //given
        ExchangeDTO exchange = new ExchangeDTO();
        exchange.setExchangeDate(new Date());
        exchange.setTitle("신청 정보 저장 테스트~");
        exchange.setMemberCode(88);

        //when
        int savedExchangeCode = exchangeService.insertExchange(exchange);

        PointFileDTO newFile = new PointFileDTO();
        newFile.setFileOriginName("파일 정보 저장 테스트~");
        newFile.setFileSaveName("바뀐 이름~");
        newFile.setFilePath("저장 경로~");
        newFile.setFileExtension("확장자~");
        newFile.setApplicationCode(savedExchangeCode);
        int savedFileApplicationCode = exchangeService.insertPointFile(newFile);

        //then
        Assertions.assertNotEquals(savedExchangeCode, 0);
        Assertions.assertNotEquals(savedFileApplicationCode, 0);
        Assertions.assertEquals(savedExchangeCode, savedFileApplicationCode);
    }

    @Test
    @DisplayName("포인트 전환 신청 목록 전체 조회 테스트")
    void testSelectAllExchanges(){
        //given

        //when
        List<ExchangeDTO> allExchanges = exchangeService.selectAllExchanges();

        //then
        Assertions.assertNotNull(allExchanges);
    }

    @Test
    @DisplayName("특정 회원의 전환 신청 목록 전체 조회 테스트")
    void testSelectMemberAllExchange(){
        //given
        int memberCode = 1;

        //when
        List<ExchangeDTO> memberAllExchange =  exchangeService.selectMemberAllExchange(memberCode);

        //then
        Assertions.assertNotNull(memberAllExchange);
    }

    @Test
    @DisplayName("포인트 전환 신청 목록 조건 조회 테스트")
    void testSelectExchangeByStatus(){
        //given
        String status = "대기";

        //when
        List<ExchangeDTO> exchangeListByStatus = exchangeService.selectExchangesByStatus(status);

        //then
        Assertions.assertNotNull(exchangeListByStatus);
    }

    @Test
    @DisplayName("전환 신청 상세 정보 조회 테스트")
    void testSelectExchangeDetail(){
        //given
        int exchangeCode = 8;

        //when
        Map<String, Object> detailResult = exchangeService.selectExchangeDetailA(exchangeCode);

        //then
        Assertions.assertNotNull(detailResult);
    }

    @Test
    @DisplayName("포인트 전환 신청 승인 테스트")
    void testExchangeApproval(){
        //given
        ExchangeDTO newExchange = new ExchangeDTO();
        newExchange.setExchangeCode(27);
        newExchange.setPoints(10000);
        newExchange.setStatus("승인");
        newExchange.setProcessingDate(new Date());

        //when
        int result = exchangeService.exchangeApproval(newExchange);

        //then
        Assertions.assertEquals(result, 1);
    }

    @Test
    @DisplayName("포인트 전환 신청 반려 테스트")
    void testExchangeRejection(){
        //given
        ExchangeDTO newExchange = new ExchangeDTO();
        newExchange.setExchangeCode(27);
        newExchange.setReturnDetail("잘못된 파일");
        newExchange.setStatus("반려");
        newExchange.setProcessingDate(new Date());

        //when
        int result = exchangeService.exchangeRejection(newExchange);

        //then
        Assertions.assertEquals(result, 1);
    }

    @Test
    @DisplayName("특정 회원 포인트 적립 / 사용 내역 조회")
    void testSelectMemberPoints(){
        //given
        int memberCode = 4;

        //when
        List<Map<String, Object>> pointHistory = exchangeService.selectMemberPoints(memberCode);

        //then
        Assertions.assertNotNull(pointHistory);
        System.out.println("포인트 내역 조회 : " + pointHistory);
    }

}
