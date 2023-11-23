package metaint.replanet.rest.bookmark.controller;

import io.swagger.v3.oas.annotations.Parameter;
import metaint.replanet.rest.auth.jwt.TokenProvider;
import metaint.replanet.rest.bookmark.dto.BookmarkDTO;
import metaint.replanet.rest.bookmark.entity.Bookmark;
import metaint.replanet.rest.bookmark.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

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
    public List<Bookmark> getBookmarkListByMember(@RequestParam String memberCode){
        return bookmarkService.getBookmarkListByMember(memberCode);
    }

    // 북마크 등록
    @PostMapping("bookmarks")
    public boolean addBookmark(@RequestBody BookmarkDTO bookmarkDTO){
        boolean check = bookmarkService.addBookmark(bookmarkDTO);
        return check;
    }

    // 북마크 삭제
    @DeleteMapping("bookmarks")
    public boolean deleteBookmark(@RequestParam String memberCode, @RequestParam String campaignCode){
        System.out.println(memberCode +"들어너?" +campaignCode);
        boolean check = bookmarkService.deleteBookmark(memberCode,campaignCode);
        System.out.println(check+ "체크좀22");
        return check;
    }

    // 북마크 전체 삭제
    @PutMapping("AllBookmarks")
    public ResponseEntity<String> deleteBookmarks(@RequestBody List<Integer> bookmarkCode){
        System.out.println(bookmarkCode +"들어너?" );
        bookmarkService.bookmarkDeleteAll(bookmarkCode);
        return null;
    }
}
