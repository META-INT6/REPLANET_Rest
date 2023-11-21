package metaint.replanet.rest.bookmark.service;

import metaint.replanet.rest.bookmark.dto.BookmarkDTO;
import metaint.replanet.rest.bookmark.entity.Bookmark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookmarkServiceTest {

    @Autowired
    BookmarkService bookmarkService;

    @DisplayName("북마크 등록 테스트")
    @Test
    void addBookmarkTest(){
        //given
        BookmarkDTO bookmarkDTO = new BookmarkDTO(1,2);

        //when
        bookmarkService.addBookmark(bookmarkDTO);
        //then
        Assertions.assertDoesNotThrow(
                () -> bookmarkService.addBookmark(bookmarkDTO)
        );

    }

    @DisplayName("북마크 삭제 테스트")
    @Test
    void deleteBookmarkTest(){
        //given

        String a = "1";
        String b = "2";
        //when
        bookmarkService.deleteBookmark(a,b);
        //then
        Assertions.assertDoesNotThrow(
                () -> bookmarkService.deleteBookmark(a,b)
        );

    }


    @DisplayName("북마크 조회 테스트")
    @Test
    void getBookmarkListTest(){
        //given
        String memberCode = "3";

        //when
        List<Bookmark> bookmarkList = bookmarkService.getBookmarkListByMember(memberCode);
        //then
        Assertions.assertNotNull(bookmarkList);
        bookmarkList.forEach(System.out::println);

    }

    
}
