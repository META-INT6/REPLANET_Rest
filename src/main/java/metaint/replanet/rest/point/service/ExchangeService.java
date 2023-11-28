package metaint.replanet.rest.point.service;

import metaint.replanet.rest.point.dto.ExchangeDTO;
import metaint.replanet.rest.point.dto.PointFileDTO;
import metaint.replanet.rest.point.entity.Exchange;
import metaint.replanet.rest.point.entity.Member;
import metaint.replanet.rest.point.entity.PointFile;
import metaint.replanet.rest.point.repository.ExchangeRepository;
import metaint.replanet.rest.point.repository.MemberPointRepository;
import metaint.replanet.rest.point.repository.PointFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    private final PointFileRepository pointFileRepository;

    private MemberPointRepository memberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final ModelMapper modelMapper;

    @Autowired
    public ExchangeService(ExchangeRepository exchangeRepository, PointFileRepository pointFileRepository, MemberPointRepository memberRepository, ModelMapper modelMapper) {
        this.exchangeRepository = exchangeRepository;
        this.pointFileRepository = pointFileRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    public int insertExchange(ExchangeDTO newExchange) {
        Exchange savedExchange = exchangeRepository.save(modelMapper.map(newExchange, Exchange.class));
        int exchangeCode = savedExchange.getExchangeCode();

        return exchangeCode;
    }

    public int insertPointFile(PointFileDTO newFile) {
        PointFile savedFile = pointFileRepository.save(modelMapper.map(newFile, PointFile.class));
        int applicationCode = savedFile.getApplicationCode();

        //실제로 쓰이지는 않지만 테스트코드를 위해 반환
        return applicationCode;
    }

    public List<ExchangeDTO> selectAllExchanges() {
        List<Exchange> allExchanges = exchangeRepository.findAll();

        allExchanges.sort(Comparator.comparingInt(Exchange::getExchangeCode));
        Collections.reverse(allExchanges);

        return allExchanges.stream()
                .map(list -> modelMapper.map(list, ExchangeDTO.class))
                .collect(Collectors.toList());
    }

    public List<ExchangeDTO> selectMemberAllExchange(int memberCode) {
        List<Exchange> memberAllExchange = exchangeRepository.findByMemberCode(memberCode);

        memberAllExchange.sort(Comparator.comparingInt(Exchange::getExchangeCode));
        Collections.reverse(memberAllExchange);

        return memberAllExchange.stream()
                .map(list -> modelMapper.map(list, ExchangeDTO.class))
                .collect(Collectors.toList());
    }

    public List<ExchangeDTO> selectExchangesByStatus(String status) {
        List<Exchange> listByStatus = exchangeRepository.findByStatus(status);

        listByStatus.sort(Comparator.comparingInt((Exchange::getExchangeCode)));
        Collections.reverse(listByStatus);

        return listByStatus.stream()
                .map(list -> modelMapper.map(list, ExchangeDTO.class))
                .collect(Collectors.toList());
    }

    public Map<String, Object> selectExchangeDetailA(int exchangeCode) {
        String sql = "SELECT e.title, e.exchange_date, e.status, e.points, e.return_detail, e.processing_date, f.file_extension, f.file_save_name, m.member_email, m.member_name " +
                "FROM tbl_point_exchange e " +
                "JOIN tbl_point_file f ON e.exchange_code = f.application_code " +
                "JOIN tbl_member m ON e.member_code = m.member_code " +
                "WHERE e.exchange_code = ?1";

        Object[] detailResult = (Object[]) entityManager.createNativeQuery(sql)
                .setParameter(1, exchangeCode)
                .getSingleResult();

        String[] columNames = {
                "title", "exchangeDate", "status", "points", "returnDetail", "processingDate", "fileExtension", "fileSaveName", "memberEmail", "memberName"
        };

        Map<String, Object> detailResultMap = new HashMap<>();
        for(int i = 0; i<columNames.length; i++){
            detailResultMap.put(columNames[i], detailResult[i]);
        }

        return detailResultMap;
    }

    public Map<String, Object> selectExchangeDetailU(int exchangeCode) {
        String sql = "SELECT e.title, e.exchange_date, e.status, e.points, e.return_detail, e.processing_date, f.file_save_name, f.file_extension " +
                "FROM tbl_point_exchange e " +
                "JOIN tbl_point_file f ON e.exchange_code = f.application_code " +
                "WHERE e.exchange_code = ?1";

        Object[] detailResultU = (Object[]) entityManager.createNativeQuery(sql)
                .setParameter(1, exchangeCode)
                .getSingleResult();

        String[] columNames = {
                "title", "exchangeDate", "status", "points", "returnDetail", "processingDate", "fileSaveName", "fileExtension"
        };

        Map<String, Object> detailResultUMap = new HashMap<>();
        for(int i = 0; i<columNames.length; i++){
            detailResultUMap.put(columNames[i], detailResultU[i]);
        }

        return detailResultUMap;
    }

    public int exchangeApproval(ExchangeDTO exchangeDTO){
        System.out.println("여기까지 오는거야??");
        Exchange approveExchange = exchangeRepository.findById(exchangeDTO.getExchangeCode()).get();
        System.out.println("approveExchange 확인 : "+approveExchange);

        if (approveExchange != null) {
            approveExchange = approveExchange.points(exchangeDTO.getPoints())
                    .status(exchangeDTO.getStatus())
                    .processingDate(new Date())
                    .builder();
            exchangeRepository.save(approveExchange);
            Member memberPoint = memberRepository.findById(approveExchange.getMemberCode()).get();
            System.out.println("적립 전 보유 포인트 : " + memberPoint.getCurrentPoint());

            if (memberPoint != null) {
                memberPoint = memberPoint.currentPoint(memberPoint.getCurrentPoint() + approveExchange.getPoints())
                        .builder();
                memberRepository.save(memberPoint);

                System.out.println("신청 상태 확인 : " + approveExchange.getStatus());
                System.out.println("승인 포인트 확인 : " + approveExchange.getPoints());
                System.out.println("현재 포인트 확인 : " + memberPoint.getCurrentPoint());

                return 1;
            }
        }
        return 0;
    }

    public int exchangeRejection(ExchangeDTO exchangeDTO) {
        Exchange rejectExchange = exchangeRepository.findById(exchangeDTO.getExchangeCode()).get();

        if (rejectExchange != null) {
            rejectExchange = rejectExchange.returnDetail(exchangeDTO.getReturnDetail())
                    .status(exchangeDTO.getStatus())
                    .processingDate(new Date())
                    .builder();
            exchangeRepository.save(rejectExchange);

            System.out.println("신청 상태 확인 : " + rejectExchange.getStatus());
            System.out.println("반려 사유 확인 : " + rejectExchange.getReturnDetail());

            return 1;
        }
        return 0;
    }

    public List<Map<String, Object>> selectMemberPoints(int memberCode) {
        String sql = "SELECT changeDate, content, changePoint, remainingPoint, status, code FROM ( " +
                "SELECT e.processing_date AS changeDate, e.title AS content, e.points AS changePoint, m.current_point AS remainingPoint, e.status AS status, e.exchange_code AS code " +
                "FROM tbl_member m " +
                "LEFT JOIN tbl_point_exchange e ON m.member_code = e.member_code " +
                "WHERE m.member_code = ?1 AND e.points > 0 " +
                "UNION " +
                "SELECT d.donation_date_time AS changeDate, c.campaign_title AS content, d.donation_point AS changePoint, m.current_point AS remainingPoint, c.campaign_category AS status, c.campaign_code AS code " +
                "FROM tbl_member m " +
                "LEFT JOIN tbl_donation d ON m.member_code = d.member_code " +
                "LEFT JOIN tbl_campaign_description c ON d.campaign_code = c.campaign_code " +
                "WHERE m.member_code = ?1 AND d.donation_point > 0 " +
                ") AS pointHistory " +
                "ORDER BY changeDate DESC";

        List<Object[]> resultList = entityManager.createNativeQuery(sql)
                .setParameter(1, memberCode)
                .getResultList();

        List<Map<String, Object>> pointHistory = new ArrayList<>();

        for(Object[] result : resultList){
            Map<String, Object> pointHistoryMap = new HashMap<>();
            pointHistoryMap.put("changeDate", result[0]);
            pointHistoryMap.put("content", result[1]);
            pointHistoryMap.put("changePoint", result[2]);
            pointHistoryMap.put("remainingPoint", result[3]);
            pointHistoryMap.put("status", result[4]);
            pointHistoryMap.put("code", result[5]);

            pointHistory.add(pointHistoryMap);
        }
        System.out.println("포인트 내역 조회 : " + pointHistory);

        return pointHistory;
    }

}
