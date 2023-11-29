package metaint.replanet.rest.reviews.model.service;

import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.reviews.dto.*;
import metaint.replanet.rest.reviews.entity.*;
import metaint.replanet.rest.reviews.repository.*;
import metaint.replanet.rest.util.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ReviewService {

    private final CampaignReviewRepository campaignReviewRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewFileRepository reviewFileRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewMemberRepository reviewMemberRepository;
    private final ModelMapper modelMapper;

    private WebMvcConfigurer corsConfigurer;


    @Value("http://localhost:3000/reviewImgs")
    public String IMAGE_URL;

    // Specify the relative path of the folder


    //Path basePath = Paths.get("User").toAbsolutePath();

    // Specify the root directory based on the operating system


    //Path relativePath = basePath.relativize(filePath);
    // Get the current working directory


    Path currentPath = Paths.get("").toAbsolutePath();

    //Path relativePath =  currentPath.relativize(filePath);

    // Resolve the relative path against the current working directory
    // Path fullPath = currentPath.resolve(relativePathString);


    public ReviewService(CampaignReviewRepository campaignReviewRepository, ReviewRepository reviewRepository, ReviewFileRepository reviewFileRepository, ReviewCommentRepository reviewCommentRepository, ReviewMemberRepository reviewMemberRepository, ModelMapper modelMapper) {
        this.campaignReviewRepository = campaignReviewRepository;
        this.reviewRepository = reviewRepository;
        this.reviewFileRepository = reviewFileRepository;
        this.reviewCommentRepository = reviewCommentRepository;
        this.reviewMemberRepository = reviewMemberRepository;
        this.modelMapper = modelMapper;
    }

    public List<CombineReviewDTO> findAllReviews() {
        List<Review> reviewList = reviewRepository.findAllOrderedByReviewCodeDesc();

        log.info("findAllReviews: " + reviewList);

        return reviewList.stream()
                .map(review -> modelMapper.map(review, CombineReviewDTO.class))
                .collect(Collectors.toList());
    }


    public CombineReviewDTO findReviewByReviewCode(Long reviewCode) {

        Review review = reviewRepository.findById(reviewCode).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(review, CombineReviewDTO.class);

    }

    @Transactional
    public void registNewReview(ReviewDTO reviewDTO, MultipartFile imageFile) throws IOException {


        log.info("[ReviewService] registNewReview Start ===========================");
        log.info("[ReviewService] registNewReview : " + reviewDTO);
        Review insertReview = modelMapper.map(reviewDTO, Review.class);

        reviewRepository.save(insertReview);
        reviewRepository.flush();

        ///////////////////////////////////////////////////////////////////////////////////
        log.info("what is the campaignCode : " + reviewDTO.getCampaignCode());
        Long campaignCode = reviewDTO.getCampaignCode();
        Long reviewCode = reviewRepository.findReviewCodeByCampaignCode(campaignCode);
        log.info("what is the fucking result??" + reviewCode);

        log.info("what is the fucking reviewCode??" + reviewCode);
        ReviewFileDTO reviewFileDTO = new ReviewFileDTO();

        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;

        //////////////////////////////////////////////////////////////////////////////
        Path rootPath;
        String IMAGE_DIR = null;
        if (FileSystems.getDefault().getSeparator().equals("/")) {
            Path MACPath = Paths.get("/REPLANET_React/public/reviewImgs").toAbsolutePath();
            // Unix-like system (MacOS, Linux)
            rootPath = Paths.get("/User").toAbsolutePath();
            Path relativePath = rootPath.relativize(MACPath);
            IMAGE_DIR = String.valueOf(relativePath);
            log.info("what is the paaaaath: " + IMAGE_DIR);

        } else {
            // Windows
            Path WinPath = Paths.get("/dev/metaint/REPLANET_React/public/reviewImgs").toAbsolutePath();
            rootPath = Paths.get("C:\\").toAbsolutePath();
            Path relativePath = rootPath.resolve(WinPath);
            IMAGE_DIR = String.valueOf(relativePath);
            rootPath = Paths.get("C:\\dev\\metaint\\").toAbsolutePath();
            Path resolvePath = rootPath.resolve(WinPath);
            IMAGE_DIR = String.valueOf(resolvePath);

        }


        log.info("what is the path: " + IMAGE_DIR);

        replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, imageFile);

        reviewFileDTO.setReviewCode(reviewCode);
        reviewFileDTO.setFileSaveName(replaceFileName);
        reviewFileDTO.setFileSavePath("http://localhost:3000" + IMAGE_DIR + "/" + replaceFileName);
        reviewFileDTO.setFileExtension("PNG");

        reviewFileDTO.setFileOriginName(imageFile.getOriginalFilename());
        reviewFileDTO.setFileOriginPath(IMAGE_DIR);


        log.info("[ReviewService] registNewReview Image name : " + replaceFileName);
        log.info("[ReviewService] registNewReview result : " + reviewDTO);
        log.info("[ReviewService] registNewReviewFile result : " + reviewFileDTO);

        ReviewFile insertReview1 = modelMapper.map(reviewFileDTO, ReviewFile.class);
        reviewFileRepository.save(insertReview1);

        result = 1;

        System.out.println("check");

        log.info("[ReviewService] registReview End ===================");
    }

    public List<CombineReviewDTO> findReviewsBySearchFilter(String searchFilter) {
        List<Review> filteredReviewList = reviewRepository.findFilteredReviews(searchFilter);

        return filteredReviewList.stream()
                .map(filteredReviews -> modelMapper.map(filteredReviews, CombineReviewDTO.class))
                .collect(Collectors.toList());
    }

    public ReviewFileDTO getThumbnailPath(Long reviewCode) {

        ReviewFile reviewFile = reviewFileRepository.findByReviewCode(reviewCode);

        log.info("reviewFile path?? : " + reviewFile);

        return modelMapper.map(reviewFile, ReviewFileDTO.class);
    }

    @Transactional
    public void modifyReview(ReviewDTO reviewDTO, MultipartFile imageFile) {

        log.info("[ProductService] updateReview Start ===================================");
        log.info("[ProductService] productDTO : " + reviewDTO);

        String replaceFileName = null;
        int result = 0;

        try {

            Review review = reviewRepository.findById(reviewDTO.getReviewCode()).get();
            ReviewFile replaceableReview = reviewFileRepository.findByReviewCode(reviewDTO.getReviewCode());
            String replaceableImgPath = replaceableReview.getFileSaveName();
            log.info("what are we deleting? : " + replaceableImgPath);

            List<ReviewFile> reviewFileList = review.getReviewFileList();

            if (!reviewFileList.isEmpty()) {
                String fileOriginpath = reviewFileList.get(0).getFileOriginPath();

                System.out.println("FileOriginpath: " + fileOriginpath);

                review = review.reviewCode(reviewDTO.getReviewCode())
                        .reviewTitle(reviewDTO.getReviewTitle())
                        .description(reviewDTO.getDescription()).build();

                if (imageFile != null) {

                    Path filePath = Paths.get("/REPLANET_React/public/reviewImgs").toAbsolutePath();
                    Path rootPath;
                    String IMAGE_DIR = null;

                    if (FileSystems.getDefault().getSeparator().equals("/")) {

                        // Unix-like system (MacOS, Linux)
                        rootPath = Paths.get("/User").toAbsolutePath();
                        Path relativePath = rootPath.relativize(filePath);
                        IMAGE_DIR = String.valueOf(relativePath);
                        log.info("what is the path: " + IMAGE_DIR);
                    } else {
                        // Windows
                        rootPath = Paths.get("C:\\").toAbsolutePath();
                        Path relativePath = rootPath.resolve(filePath);
                        IMAGE_DIR = String.valueOf(relativePath);

                    }


                    log.info("what is the path: " + IMAGE_DIR);

                    String imageName = UUID.randomUUID().toString().replace("-", "");
                    replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, imageFile);

                    ////////////////////////////


                    ReviewFile reviewFile = reviewFileRepository.findByReviewCode(reviewDTO.getReviewCode());

                    //ReviewFileDTO reviewFileDTO = new ReviewFileDTO();

                    reviewFile = reviewFile.fileSaveName(replaceFileName).build();

                    boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, replaceableImgPath);
                    log.info("[Update Review] isDelete : " + isDelete);

                    log.info("[Update Review] InserFileName : " + replaceFileName);
                }
            } else {
                ReviewFile reviewFile = reviewFileRepository.findByReviewCode(reviewDTO.getReviewCode());
                reviewFile = reviewFile.fileSaveName(replaceableImgPath).build();

                System.out.println("No files in the reviewFileList");
            }
        } catch (Exception e) {
            System.out.println("Error occurred!");
        }
    }

    @Transactional
    public void deleteReview(Long reviewCode, Long revFileCode) {

        ReviewFile replaceableReview = reviewFileRepository.findByReviewCode(reviewCode);
        String deleteImgPath = replaceableReview.getFileSaveName();

        Path filePath = Paths.get("/REPLANET_ReactAPI/public/reviewImgs").toAbsolutePath();
        Path rootPath;
        String IMAGE_DIR = null;
        if (FileSystems.getDefault().getSeparator().equals("/")) {

            // Unix-like system (MacOS, Linux)
            rootPath = Paths.get("/User").toAbsolutePath();
            Path relativePath = rootPath.relativize(filePath);
            IMAGE_DIR = String.valueOf(relativePath);
            log.info("what is the paaaaath: " + IMAGE_DIR);
        } else {
            // Windows
            rootPath = Paths.get("C:\\").toAbsolutePath();
            Path relativePath = rootPath.resolve(filePath);
            IMAGE_DIR = String.valueOf(relativePath);
            log.info("what is the paaaaath: " + IMAGE_DIR);
        }


        log.info("what is the path: " + IMAGE_DIR);

        boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, deleteImgPath);
        log.info("[Delete Review] isDelete : " + isDelete);
        log.info("[Delete Review] deleting reviewCode : " + reviewCode);

        //reviewRepository.deleteById(reviewCode);
        try {
            reviewRepository.deleteByReviewCode(reviewCode);
            reviewFileRepository.deleteByRevFileCode(revFileCode);
            reviewCommentRepository.deleteByReviewCode(reviewCode);
        } catch (Exception e) {
            System.out.println("Error occurred during review deletion!");
            e.printStackTrace();
        }
    }

    public void registNewComment(Long reviewCode, ReviewCommentDTO reviewCommentDTO) {

        // 현재 날짜
        LocalDateTime date = LocalDateTime.now();
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        reviewCommentDTO.setRevCommentDate(date);

        ReviewComment comment = modelMapper.map(reviewCommentDTO, ReviewComment.class);
        reviewCommentRepository.save(comment);

        log.info("hihihihi:" + reviewCode + reviewCommentDTO);
    }

    public CombineReviewDTO findAllCommentsByReviewCode(Long reviewCode, CombineReviewDTO details) {

        List<ReviewComment> reviewComment = reviewCommentRepository.findAllByReviewCode(reviewCode);


        if (!reviewComment.isEmpty()) {
            details.setReviewCommentList(modelMapper.map(reviewComment, new TypeToken<List<ReviewComment>>() {
            }.getType()));
            return details;
        } else {
            return details;
        }
    }

    @Transactional
    public void deleteReviewComment(Long revCommentCode) {

        try {
            reviewCommentRepository.deleteById(revCommentCode);
            log.info("[Review Service] deleteReviewComment : 리뷰 댓글 삭제 성공!");
        } catch (Exception e) {
            log.info("[Review Service] deleteReviewComment : 리뷰 댓글 삭제 실패!");
            e.printStackTrace();
        }
    }

    @Transactional
    public void modifyReviewComment(ReviewCommentDTO reviewCommentDTO) {

        ReviewComment reviewComment = reviewCommentRepository.findById(reviewCommentDTO.getRevCommentCode()).get();

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        // use builder for modifying!!!!  reviewCommentRepository.
        reviewComment = reviewComment.reviewCommentCode(reviewCommentDTO.getRevCommentCode())
                .revCommentContent(reviewCommentDTO.getRevCommentContent())
                .memberCode(reviewCommentDTO.getMemberCode())
                .revCommentDate(date)
                .reviewCode(reviewCommentDTO.getReviewCode())
                .build();

        reviewCommentRepository.save(reviewComment);
    }

    public void monitorComment(Long revCommentCode) {
        reviewCommentRepository.updateRevCommentMonitorized(revCommentCode);

    }

    public String getMemberEmailByMemberCode(Long memberCode) {

        log.info("[Review Service] getMemberEmail memberCode : " + memberCode);
        return reviewMemberRepository.findEmailByMemberCode(memberCode);
        //return modelMapper.map(memberEmail, MemberDTO.class);
    }

    public List<Campaign> findUnassociatedCampaigns() {

        return campaignReviewRepository.findUnassociatedCampaigns();
    }

    public String uploadImage(MultipartFile file) {
        try {
            // Validate the file if needed
            log.info("my file>?!!?: " + file);
            if (file.getSize() == 0) {
                log.info("Empty file");
                throw new IllegalArgumentException("Empty file");
            }

            // Generate a random UUID for the file name
            String randomFileName = UUID.randomUUID().toString();

            // Get the original file extension
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));

            // Specify the destination folder with the random file name and original file extension
            String filePath = "/Users/babyybiss/Documents/FullStackJava/REPLANET_React/public/reviewImgs/" + randomFileName + fileExtension;
            String fileName = randomFileName + fileExtension;
            // Save the file
            file.transferTo(new File(filePath));

            // Respond with the file path or any other relevant information
            log.info("File uploaded successfully. Path: " + filePath);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error", e);
        }
    }
}