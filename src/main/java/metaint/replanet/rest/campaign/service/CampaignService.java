package metaint.replanet.rest.campaign.service;

import metaint.replanet.rest.campaign.dto.CampaignDescFileDTO;
import metaint.replanet.rest.campaign.dto.CampaignDescriptionDTO;
import metaint.replanet.rest.campaign.dto.TodayDonationsDTO;
import metaint.replanet.rest.campaign.entity.*;
import metaint.replanet.rest.campaign.repository.CampaignAndFileRepository;
import metaint.replanet.rest.campaign.repository.CampaignFileRepository;
import metaint.replanet.rest.campaign.repository.CampaignRepository;
import metaint.replanet.rest.campaign.repository.ParticipationRepository;
import metaint.replanet.rest.util.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CampaignService {


    private final CampaignRepository campaignRepository;
    private final CampaignFileRepository campaignFileRepository;

    private final CampaignAndFileRepository campaignAndFileRepository;
    private final ParticipationRepository participationRepository;
    private final ModelMapper modelMapper;

    @Value("C:\\filetest")
    private String IMAGE_DIR;

    @Value("http://localhost:3000/campaigns")
    public String IMAGE_URL;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository, CampaignFileRepository campaignFileRepository, CampaignAndFileRepository campaignAndFileRepository, ParticipationRepository participationRepository, ModelMapper modelMapper) {
        this.campaignRepository = campaignRepository;
        this.campaignFileRepository = campaignFileRepository;
        this.campaignAndFileRepository = campaignAndFileRepository;
        this.participationRepository = participationRepository;
        this.modelMapper = modelMapper;
    }

    //등록 성공
    @Transactional
    public int registCampaign(CampaignDescriptionDTO campaign) {
        // 목표금액 , 제거
        String goalBudger = campaign.getGoalBudget().replaceAll(",", "");
        campaign.setGoalBudget(goalBudger);
        Double overGoalBudger = Double.parseDouble(goalBudger);

        // 금액 체크
        if (overGoalBudger > 1000000000) {
            return -2;
        }

        // 현재 날짜
        LocalDateTime startDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        campaign.setStartDate(startDate);
        System.out.println(startDate + "스타트 데이트");
        // 마감일 형변환 String =>  LocalDateTime
        String getEndDate = campaign.getEndDate();
        LocalDateTime endDate = LocalDateTime.parse(getEndDate + "T23:59:59", formatter);
        if (endDate.isBefore(startDate)) {
            return -1;
        }


        // 변환된 LocalDateTime을 Entity에 매핑
        CampaignDescription campaignEntity = modelMapper.map(campaign, CampaignDescription.class);


        campaignEntity.endDate(endDate).builder();

        campaignRepository.save(campaignEntity);
        System.out.println(campaignEntity.getCampaignCode() + " 여기가 캠페인 코드다!!");

        return campaignEntity.getCampaignCode();
    }

    // 파일 등록 성공
    @Transactional
    public String registImage(MultipartFile imageFile, int campaignCode) {

        CampaignDescFileDTO campaignFileDTO = new CampaignDescFileDTO();

        String fileSaveName = null;
        int result = 0;

        Path rootPath;
        String IMAGE_DIR = null;
        if (FileSystems.getDefault().getSeparator().equals("/")) {
            Path MACPath = Paths.get("/REPLANET_ReactAPI/public/campaigns").toAbsolutePath();
            // Unix-like system (MacOS, Linux)
            rootPath = Paths.get("/User").toAbsolutePath();
            Path relativePath = rootPath.relativize(MACPath);
            IMAGE_DIR = String.valueOf(relativePath);
        } else {
            // Windows
            Path WinPath = Paths.get("/dev/metaint/REPLANET_React/public/campaigns").toAbsolutePath();
            rootPath = Paths.get("C:\\").toAbsolutePath();
            Path relativePath = rootPath.resolve(WinPath);
            IMAGE_DIR = String.valueOf(relativePath);
        }


        try {
            // 파일 정보 가져오기
            String fileOriginName = imageFile.getOriginalFilename();
            String fileExtension = fileOriginName.substring(fileOriginName.lastIndexOf("."));
            String imageName = UUID.randomUUID().toString().replaceAll("-", "");

            fileSaveName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, imageFile);

            // 파일 정보 세팅
            campaignFileDTO.setFileOriginName(fileOriginName);
            campaignFileDTO.setFileOriginPath("얜 필요 없을거가틈");
            campaignFileDTO.setFileSaveName(fileSaveName);
            campaignFileDTO.setFileSavePath(IMAGE_URL);
            campaignFileDTO.setFileExtension(fileExtension);
            campaignFileDTO.setCampaignCode(campaignCode);
            //campaignFile.setFileSaveName(replaceFileName);
            CampaignDescFile campaignDescFile = modelMapper.map(campaignFileDTO, CampaignDescFile.class);
            campaignFileRepository.save(campaignDescFile);
            result = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (result > 0) ? "성공" : "실패";
    }


    // 전체 진행중 조회 성공
    public List<CampaignAndFile> findCampaignList() {
        LocalDateTime currentDate = LocalDateTime.now();
        List<CampaignAndFile> campaignList = campaignAndFileRepository.findByEndDateAfter(currentDate);
        return campaignList;
    }

    // 전체 종료 조회 성공
    public List<CampaignAndFile> findCampaignListDone() {
        LocalDateTime currentDate = LocalDateTime.now();
        List<CampaignAndFile> campaignList = campaignAndFileRepository.findByEndDateBefore(currentDate);
        return campaignList;
    }

    // 상세 조회 성공
    public CampaignAndFile findCampaign(int campaignCode) {
        CampaignAndFile findCampaign = campaignAndFileRepository.findById(campaignCode).orElseThrow(IllegalArgumentException::new);
        //CampaignDescription findCampaign = campaignRepository.findById(campaignCode).orElseThrow(IllegalArgumentException::new);
        return findCampaign;
    }

//    // 카테고리별 리스트 조회
//    public List<CampaignAndFile>  findCategoryByCampaignList(String category) {
//        System.out.println(category + "카테고리 확인 1111");
//        List<CampaignDescription> findCategoryByCampaignList = campaignRepository.findByCampaignCategory(category);
//        return findCategoryByCampaignList;
//    }

    // 종료 임박 순 조회 성공
    public List<CampaignAndFile> findCampaignSort(LocalDateTime currentDate) {
        List<CampaignAndFile> findCampaignSort = campaignAndFileRepository.findCampaignSort(currentDate);
        System.out.println(findCampaignSort + "잘받아오는지 화긴해보자");

        return findCampaignSort;
    }


    // 캠페인 삭제 성공
    @Transactional
    public void deleteCampaign(int campaignCode) {
        System.out.println(campaignCode + "여기는 캠펜코드");

        List<CampaignDescFile> campaign = campaignFileRepository.findByCampaignCodeCampaignCode(campaignCode);
        System.out.println(campaign + "화긴");
        if (campaign != null) {
            campaignFileRepository.deleteByCampaignCodeCampaignCode(campaignCode);
            campaignRepository.deleteById(campaignCode);

        }


    }

    // 캠페인 수정
    @Transactional
    public int modifyCampaign(CampaignDescriptionDTO campaignDTO, int campaignCode, MultipartFile imageFile) {
        // 이미지 파일 있음 삭제
        if (imageFile != null) {
            campaignFileRepository.deleteByCampaignCodeCampaignCode(campaignCode);
        } else {
            int result;
            // update 할 엔티티 조회
            CampaignAndFile campaign = campaignAndFileRepository.findById(campaignCode).get();

            //List<CampaignDescFile> file = campaignFileRepository.findByCampaignCodeCampaignCode(campaignCode);
            // 목표금액 , 제거
            String goalBudger = campaignDTO.getGoalBudget().replaceAll(",", "");
            campaignDTO.setGoalBudget(goalBudger);
            Double overGoalBudger = Double.parseDouble(goalBudger);
            System.out.println(imageFile + "아거 gd ");

            // 금액 체크
            if (overGoalBudger > 1000000000) {
                result = -2;
                return result;
            } else {
                System.out.println(campaignDTO + "아거 확인11좀 ");

                // 현재 날짜
                LocalDateTime startDate = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                campaignDTO.setStartDate(startDate);
                System.out.println(startDate + "스타트 데이트");
                // 마감일 형변환 String =>  LocalDateTime
                LocalDateTime endDate;

                String getEndDate = campaignDTO.getEndDate();
                System.out.println(getEndDate + "앤드데이트");
                if (getEndDate.contains("-")) {
                    endDate = LocalDateTime.parse(getEndDate + "T23:59:59", formatter);
                } else {
                    String[] dateComponents = getEndDate.split(",");

                    int year = Integer.parseInt(dateComponents[0]);
                    int month = Integer.parseInt(dateComponents[1]);
                    int day = Integer.parseInt(dateComponents[2]);
                    int hour = Integer.parseInt(dateComponents[3]);
                    int minute = Integer.parseInt(dateComponents[4]);
                    int second = Integer.parseInt(dateComponents[5]);

                    endDate = LocalDateTime.of(year, month, day, hour, minute, second);
                }
                if (endDate.isBefore(startDate)) {
                    result = -1;
                    return result;
                }

                // 변환된 LocalDateTime을 Entity에 매핑
                CampaignDescription campaignEntity = modelMapper.map(campaign, CampaignDescription.class);
                campaignEntity.endDate(endDate).builder();
                System.out.println(campaign + "아거 확인22좀 ");

                // update를 위한 엔티티 값 수정
                campaign = campaign.campaignTitle(campaignDTO.getCampaignTitle())
                        .campaignContent(campaignDTO.getCampaignContent())
                        .endDate(endDate)
                        .campaignCategory(campaignDTO.getCampaignCategory())
                        .goalBudget(Integer.parseInt(campaignDTO.getGoalBudget().replaceAll(",", "")))
                        .orgName(campaignDTO.getOrgName())
                        .orgDescription(campaignDTO.getOrgDescription())
                        .orgTel(campaignDTO.getOrgTel()).builder();
            }
        }
        return 0;
    }

    public List<Pay> findparticipationList(int campaignCode) {
        List<Pay> payList = participationRepository.findByDonationByCampaignCode(campaignCode);
        return payList;
    }

    // 일일 모금현황 조회
    public List<TodayDonationsDTO> getTodayDonations() {
        List<Object[]> today = participationRepository.findByTodayDonation();
        System.out.println(today + "이거 확인좀");

        List<TodayDonationsDTO> list = new ArrayList<>();
        for (Object[] objects : today) {
            int payCode = ((Number) objects[0]).intValue();
            int payAmount = ((Number) objects[1]).intValue();
            String donationDate = (String) objects[2];
            int donationCount = ((Number) objects[3]).intValue();
            int totalDonation = ((Number) objects[4]).intValue();

            TodayDonationsDTO todayDTO = new TodayDonationsDTO();
            todayDTO.setPayCode(payCode);
            todayDTO.setPayAmount(payAmount);
            todayDTO.setDonationDate(donationDate);
            todayDTO.setDonationCount(donationCount);
            todayDTO.setTotalDonation(totalDonation);
            System.out.println(todayDTO + "화긴");
            TodayDonationsDTO apply = todayDTO;
            list.add(apply);
        }
        return list;
    }


}
