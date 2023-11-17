package metaint.replanet.rest.reviews.model.service;

import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.reviews.dto.*;
import metaint.replanet.rest.reviews.entity.Campaign;
import metaint.replanet.rest.reviews.entity.Review;
import metaint.replanet.rest.reviews.entity.ReviewComment;
import metaint.replanet.rest.reviews.entity.ReviewFile;
import metaint.replanet.rest.reviews.repository.CampaignReviewRepository;
import metaint.replanet.rest.reviews.repository.ReviewCommentRepository;
import metaint.replanet.rest.reviews.repository.ReviewFileRepository;
import metaint.replanet.rest.reviews.repository.ReviewRepository;
import metaint.replanet.rest.util.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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



    public ReviewService(CampaignReviewRepository campaignReviewRepository, ReviewRepository reviewRepository, ReviewFileRepository reviewFileRepository, ReviewCommentRepository reviewCommentRepository, ModelMapper modelMapper) {
        this.campaignReviewRepository = campaignReviewRepository;
        this.reviewRepository = reviewRepository;
        this.reviewFileRepository = reviewFileRepository;
        this.reviewCommentRepository = reviewCommentRepository;
        this.modelMapper = modelMapper;
    }

    public List<CombineReviewDTO> findAllReviews() {
        List<Campaign> reviewList = campaignReviewRepository.findAllOrderedByCampaignCodeDesc();

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
        Long reviewCode = reviewRepository.findByCampaignCode(reviewDTO.getCampaignCode());

        //System.out.println("what is the fucking reviewCode??" + reviewCode);
        ReviewFileDTO reviewFileDTO = new ReviewFileDTO();

        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;

        //////////////////////////////////////////////////////////////////////////////
        Path rootPath;
        String IMAGE_DIR = null;
        if (FileSystems.getDefault().getSeparator().equals("/")) {
            Path MACPath = Paths.get("/REPLANET_ReactAPI/public/reviewImgs").toAbsolutePath();
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

            if(!reviewFileList.isEmpty()) {
                String fileOriginpath = reviewFileList.get(0).getFileOriginPath();

                System.out.println("FileOriginpath: " + fileOriginpath);

                review = review.reviewCode(reviewDTO.getReviewCode())
                        .reviewTitle(reviewDTO.getReviewTitle())
                        .description(reviewDTO.getDescription()).build();

                if(imageFile != null) {

                    Path filePath = Paths.get("/REPLANET_ReactAPI/public/reviewImgs").toAbsolutePath();
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
            details.setReviewCommentList(modelMapper.map(reviewComment, new TypeToken<List<ReviewComment>>() {}.getType()));
            return details;
        } else {
            return details;
        }
    }



    /*public String uploadFiles(MultipartFile file) {

        ReviewFileDTO reviewFileDTO = new ReviewFileDTO();
        reviewFileDTO.setFileOriginName("http://localhost:3000/public/reviewImgs/" + file.getOriginalFilename());
        reviewFileDTO.setFileOriginPath("somePath");
        reviewFileDTO.setFileSaveName(file.getOriginalFilename());
        reviewFileDTO.setFileSavePath("http://localhost:3000/public/reviewImgs/" + file.getOriginalFilename());
        reviewFileDTO.setFileExtension(file.getContentType());

        ReviewFile insertReview = modelMapper.map(reviewFileDTO, ReviewFile.class);
        reviewFileRepository.save(insertReview);

        return insertReview.getFileSavePath();
    }*/
}
