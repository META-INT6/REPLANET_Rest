package metaint.replanet.rest.reviews.model.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.auth.jwt.TokenProvider;
import metaint.replanet.rest.reviews.dto.*;
import metaint.replanet.rest.reviews.entity.Campaign;
import metaint.replanet.rest.reviews.model.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class ReviewController {
    private static final Logger logger = Logger.getLogger(ReviewController.class.getName());
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping("/")
    public ResponseEntity<List<CombineReviewDTO>> getReviewList() {

        log.info("(ReviewController) 들어옴");

        List<CombineReviewDTO> details = reviewService.findAllReviews();

        log.info("(review controller): 가져온 총 결과 : " +details);
        return  ResponseEntity.ok(details);
    }

    @GetMapping("/{reviewCode}")
    public ResponseEntity<CombineReviewDTO> getSpecificReview(@PathVariable Long reviewCode) {
        log.info("(ReviewController) getSpecificReview code : " + reviewCode);

        CombineReviewDTO details = reviewService.findReviewByReviewCode(reviewCode);

        details = reviewService.findAllCommentsByReviewCode(reviewCode, details);

        log.info("(review controller): 가져온 총 결과 : " +details);
        return ResponseEntity.ok(details);
    }

    @PostMapping("/")
    public ResponseEntity<?> registReview(@ModelAttribute ReviewDTO reviewDTO,
                                          MultipartFile imageFile) throws IOException {

        log.info("(Review Controller) RegistReview : " + reviewDTO);
        log.info("(Review Controller) RegistReview Image : " + imageFile);

        reviewService.registNewReview(reviewDTO, imageFile);

        return ResponseEntity.ok("신규 리뷰 등록 성공!");
    }

    /*@PostMapping("/uploadImage")
    public ResponseEntity<String> handleFileUpload(@RequestParam("multipartFiles") MultipartFile file) {

        log.info("(Review Controller) handleFileUpload : " + file.getOriginalFilename());

        String imageUrl = reviewService.uploadFiles(file);


        return ResponseEntity.ok(imageUrl);

    }*/



    @GetMapping("")
    public ResponseEntity<List<CombineReviewDTO>> getReviewsBySearchFilter(@RequestParam(name = "sort") String searchFilter) {
        log.info("(ReviewController) getReviewsBySearchFilter : " + searchFilter);
        log.info("(ReviewController) getReviewsBySearchFilter : " + searchFilter);

        List<CombineReviewDTO> details = reviewService.findReviewsBySearchFilter(searchFilter);

        return ResponseEntity.ok(details);
    }

    @GetMapping("/thumbnail/{reviewCode}")
    public ResponseEntity<ReviewFileDTO> getThumbnailPath(@PathVariable Long reviewCode) {

        ReviewFileDTO details = reviewService.getThumbnailPath(reviewCode);

        log.info("(review controller): 가져온 썸네일 경로 결과 : " + details);
        return ResponseEntity.ok(details);
    }

    @PutMapping("/")
    public ResponseEntity<?> modifyReview(@ModelAttribute ReviewDTO reviewDTO,
                                          MultipartFile imageFile) throws IOException {

        log.info("(Review Controller) RegistReview : " + reviewDTO);
        log.info("(Review Controller) RegistReview Image : " + imageFile);

        reviewService.modifyReview(reviewDTO, imageFile);

        return ResponseEntity.ok("리뷰 수정 성공!");
    }


    @DeleteMapping("{reviewCode}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewCode, @RequestParam Long revFileCode) {
        log.info("[Review Controller] delete Review : " + reviewCode + "and revFileCode : " + revFileCode);

        reviewService.deleteReview(reviewCode, revFileCode);

        return ResponseEntity.ok("리뷰 삭제 성공!");
    }

    @GetMapping("/memberCode")
    public ResponseEntity<Long> getMemberCode(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader){
        String token = extractToken(authorizationHeader);
        Authentication authentication = tokenProvider.getAuthentication(token);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String memberCode = userDetails.getUsername();

        // Convert memberCode to Long if it's a numeric value
        Long memberCodeLong = Long.parseLong(memberCode);

        log.info("[/kakaoPay memberCode] : " + memberCodeLong);

        // Return memberCode in the response body
        return new ResponseEntity<>(memberCodeLong, HttpStatus.OK);
    }

    private String extractToken(String authorizationHeader) {
        log.info("[extractToken(String authorizationHeader)] ----------------------------------------------");
        log.info("[extractToken() authorizationHeader] : " + authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            log.info("[extractToken()] 토큰 추출 성공 ");
            return authorizationHeader.substring(7);
        }
        log.info("[extractToken()] 토큰 추출 실패 ");
        return null;
    }


    @PostMapping("/{reviewCode}/comments")
    public ResponseEntity<?> registNewComment(@PathVariable Long reviewCode, @ModelAttribute ReviewCommentDTO reviewCommentDTO){

        log.info("[Review Controller] regist Comment : " + reviewCommentDTO);
        log.info("[Review Controller] regist Comment : " + reviewCode);
        reviewService.registNewComment(reviewCode, reviewCommentDTO);
        return ResponseEntity.ok("신규 댓글 등록 성공!");
    }

    @DeleteMapping("/{reviewCode}/comments/{revCommentCode}")
    public ResponseEntity<?> deleteSpecificReviewComment(@PathVariable Long reviewCode, @PathVariable Long revCommentCode) {
        log.info("[Review Controller] deleteSpecificReviewComment reviewCode : " + reviewCode);
        log.info("[Review Controller] deleteSpecificReviewComment revCommentCode : " + revCommentCode);

        reviewService.deleteReviewComment(revCommentCode);

        return ResponseEntity.ok("리뷰 댓글 삭제 성공!");
    }

    @PutMapping("/{reviewCode}/comments/{revCommentCode}")
    public ResponseEntity<?> modifySpecificComment(@PathVariable Long reviewCode, @PathVariable Long revCommentCode, @ModelAttribute ReviewCommentDTO reviewCommentDTO) {
        log.info("[Review Controller] modifySpecificComment reviewCode : " + reviewCode);
        log.info("[Review Controller] modifySpecificComment revCommentCode : " + revCommentCode);
        log.info("[Review Controller] modifySpecificComment form : " + reviewCommentDTO);

        reviewService.modifyReviewComment(reviewCommentDTO);

        return ResponseEntity.ok("[Review Controller] modifySpecificComment : " + revCommentCode + "번 코드 리뷰 댓글 수정 완료!");
    }

    @PutMapping("/{reviewCode}/monitoredComment/{revCommentCode}")
    public ResponseEntity<?> monitorComment(@PathVariable Long revCommentCode) {
        reviewService.monitorComment(revCommentCode);

        return ResponseEntity.ok("[Review Controller] modifySpecificComment : " + revCommentCode + "번 코드 리뷰 댓글 숨김 모드 완료!");
    }

    @GetMapping("/getEmailByMemberCode/{memberCode}")
    public ResponseEntity<?> getMemberEmail(@PathVariable Long memberCode) {
        log.info("[Review Controller] getMemberEmail memberCode : " + memberCode);

        String email = reviewService.getMemberEmailByMemberCode(memberCode);

        //String email = getEmail.getMemberEmail();
        log.info("[Review Controller] getMemberEmail received Email : " + email);
        return ResponseEntity.ok(email);
    }

    @GetMapping("/nonExisting")
    public ResponseEntity<List<Campaign>> getNonExistingReviewCampaigns() {
        List<Campaign> unassociatedCampaigns = reviewService.findUnassociatedCampaigns();

        return ResponseEntity.ok(unassociatedCampaigns);
    }

/*    @PostMapping("/imageUpload")
    public ResponseEntity<String> handleImageUpload(@RequestParam("imageFile") MultipartFile file) {

        log.info("what is the imageFile : " + file);

        String data = reviewService.uploadImage(file);
        log.info("wtf is this : " + ResponseEntity.ok(data));
        log.info("wtf is this : " + data);
        return ResponseEntity.ok(data);

    }*/

    @PostMapping("/imageUpload")
    public ResponseEntity<String> handleImageUpload(@RequestParam("file") MultipartFile file) {
        try {
            log.info("[Review controller] image? : " + file);
            String imageUrl = reviewService.uploadImage(file);

            // Create a success response
          //  ImageUploadResponse response = new ImageUploadResponse(imageUrl, true);

            // Convert the response to JSON
            ObjectMapper objectMapper = new ObjectMapper();
           // String jsonResponse = objectMapper.writeValueAsString(response);
            log.info("whats the resposne?: " + imageUrl);
            // Return the JSON response with the random file name
            return ResponseEntity.ok("{\"imageUrl\": \"/reviewImgs/" + imageUrl + "\"}");


        } catch (Exception e) {
            // Create a failure response
            ImageUploadResponse response = new ImageUploadResponse(false, e.getMessage());

            // Convert the response to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse;
            try {
                jsonResponse = objectMapper.writeValueAsString(response);
            } catch (JsonProcessingException jsonProcessingException) {
                jsonResponse = "{\"status\":false,\"message\":\"Error processing response\"}";
            }

            // Return the JSON response with the random file name
            return ResponseEntity.ok(jsonResponse);
        }
    }



}

