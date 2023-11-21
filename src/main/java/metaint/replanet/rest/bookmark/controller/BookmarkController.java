package metaint.replanet.rest.bookmark.controller;

import io.swagger.v3.oas.annotations.Parameter;
import metaint.replanet.rest.auth.jwt.TokenProvider;
import metaint.replanet.rest.bookmark.dto.BookmarkDTO;
import metaint.replanet.rest.bookmark.entity.Bookmark;
import metaint.replanet.rest.bookmark.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(value = {"/"})
public class BookmarkController {
    private BookmarkService bookmarkService;

    private TokenProvider tokenProvider;
    @Autowired
    public BookmarkController(BookmarkService bookmarkService){
        this.bookmarkService = bookmarkService;
    }

    //북마크 전체조회
    @GetMapping("bookmarks")
    public List<Bookmark> getBookmarkListByMember(@RequestBody BookmarkDTO bookmarkDTO
            /*@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader*/){
//        System.out.println(authorizationHeader + "헤더");
//
//        String token = extractToken(authorizationHeader);
//        Authentication authentication = tokenProvider.getAuthentication(token);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String memberCode = "1";

        System.out.println(bookmarkDTO+"멤코코");



        return bookmarkService.getBookmarkListByMember(memberCode);
    }

    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("이게안되는듯");
            return authorizationHeader.substring(7);
        }
        return null;
    }

    // 북마크 등록
    @PostMapping("bookmarks")
    public boolean addBookmark(@RequestBody BookmarkDTO bookmarkDTO){
        System.out.println(bookmarkDTO +"들어너?" );
        boolean check = bookmarkService.addBookmark(bookmarkDTO);
        System.out.println(check+ "체크좀");
        return check;
    }

    // 북마크 삭제
    @DeleteMapping("bookmarks")
    public boolean deleteBookmark(@RequestParam String memberCode, @RequestParam String campaignCode){
        System.out.println(memberCode +"들어너?" +campaignCode);
        boolean check = bookmarkService.deleteBookmark(memberCode,campaignCode);
        System.out.println(check+ "체크좀22");
        return true;
    }



}
