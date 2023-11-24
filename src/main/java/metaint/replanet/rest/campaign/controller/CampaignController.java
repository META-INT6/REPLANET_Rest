package metaint.replanet.rest.campaign.controller;

import io.swagger.v3.oas.annotations.Parameter;
import metaint.replanet.rest.campaign.dto.CampaignDescFileDTO;
import metaint.replanet.rest.campaign.dto.CampaignDescriptionDTO;
import metaint.replanet.rest.campaign.dto.TodayDonationsDTO;
import metaint.replanet.rest.campaign.entity.CampaignAndFile;
import metaint.replanet.rest.campaign.entity.CampaignDescription;
import metaint.replanet.rest.campaign.entity.Donation;
import metaint.replanet.rest.campaign.entity.Pay;
import metaint.replanet.rest.campaign.service.CampaignService;
import metaint.replanet.rest.point.dto.PointFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(value = {"/", "campaigns"})
public class CampaignController {

    private CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    // 진행중 조회 
    @GetMapping()
    public List<CampaignAndFile> main() {
        return campaignService.findCampaignList();
    }

    // 종료 조회
    @GetMapping("done")
    public List<CampaignAndFile> campaignListDone() {
        return campaignService.findCampaignListDone();
    }

    // 상세 조회
    @GetMapping("{campaignCode}")
    public CampaignAndFile campaignDetail(@PathVariable int campaignCode) {
        System.out.println(campaignCode);
        return campaignService.findCampaign(campaignCode);
    }
    // 기부내역 조회
    @GetMapping("donations/{campaignCode}")
    public List<Pay> donationByCampaignCode(@PathVariable int campaignCode) {
        System.out.println(campaignCode+"ㅇㅇㅇ");
        return campaignService.findparticipationList(campaignCode);
    }

    // 카테고리별 조회
    @GetMapping("/category")
    public List<CampaignDescription> findCategoryByCampaignList (@Parameter String category){
        System.out.println("카테고리 잘 받아옴?");
        return campaignService.findCategoryByCampaignList(category);
    }


    // 캠페인 등록
    @PostMapping(name = "campaigns", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> campaignRegist(@ModelAttribute CampaignDescriptionDTO campaign, MultipartFile imageFile) throws IOException {

        System.out.println(campaign);
        System.out.println(imageFile);
        if (campaign != null && imageFile != null) {
            int campaignCode = campaignService.registCampaign(campaign);

            if (campaignCode == -1) {
                System.out.println("마감일은 현재날짜보다 커야함");
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("마감일은 현재날짜보다 커야함");
            }
            if (campaignCode == -2) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("10억 이상은 좀.. ");
            }
            campaignService.registImage(imageFile, campaignCode);

        } else {
            if (campaign != null) {
                System.out.println("여기지?");
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("사진 없음");
            } else if (imageFile != null) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("내용 없음");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("등록 성공");
    }

    // 캠페인 삭제 기부내역 없을 경우에만
    @DeleteMapping("campaigns/{campaignCode}")
    public ResponseEntity<?> campaignDelete(@PathVariable int campaignCode) {

        CampaignAndFile campaign = campaignService.findCampaign(campaignCode);

        int currentBudget = campaign.getCurrentBudget();
        if (currentBudget > 0) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("모금액 있어서 삭제 안됨");
        } else {
            campaignService.deleteCampaign(campaignCode);
            return ResponseEntity.status(HttpStatus.OK).body("삭제됨");
        }
    }

    // 캠페인 수정 기부내역 없을 경우에만
    @PutMapping("campaigns/{campaignCode}")
    public ResponseEntity<?> campaignModify(@ModelAttribute CampaignDescriptionDTO campaignDTO, MultipartFile imageFile) {

        System.out.println(imageFile + "임지");
        int campaignCode = campaignDTO.getCampaignCode();

        CampaignAndFile campaign = campaignService.findCampaign(campaignCode);

        // 현재 모금액 확인
        int currentBudget = campaign.getCurrentBudget();
        LocalDateTime endDate = campaign.getEndDate();
        LocalDateTime currentDate = LocalDateTime.now();

        System.out.println(currentBudget);
        if ((currentBudget > 0) || (endDate.isBefore(currentDate))) {
            System.out.println("모금액 있어서 수정 안됨");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("모금액 있거나 마감일이 지나서 수정 안됨");
        } else {
            int result = campaignService.modifyCampaign(campaignDTO, campaignCode, imageFile);
            if (result == -1) {
                System.out.println("마감일은 현재날짜보다 커야함");
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("마감일은 현재날짜보다 커야함");
            }
            if (result == -2) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("10억 이상은 좀.. ");
            }
            if (imageFile != null) {
                campaignService.registImage(imageFile, campaignCode);

            }
            System.out.println("수정됨");
        }
        return ResponseEntity.status(HttpStatus.OK).body("성공");
        //return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공")
    }

    // 하루 총 기부금액,횟수 조회
    @GetMapping("donations/today")
    public List<TodayDonationsDTO> getTodayDonations(){

        return campaignService.getTodayDonations();
    }





}

