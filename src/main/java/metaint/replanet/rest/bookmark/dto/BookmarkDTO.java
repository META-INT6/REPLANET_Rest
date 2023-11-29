package metaint.replanet.rest.bookmark.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookmarkDTO {

    private int bookmarkCode;
    private int memberCode;
    private int campaignCode;

    public BookmarkDTO(int memberCode, int campaignCode) {
        this.memberCode = memberCode;
        this.campaignCode = campaignCode;
    }
}
