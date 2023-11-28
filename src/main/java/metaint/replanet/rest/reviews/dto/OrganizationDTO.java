package metaint.replanet.rest.reviews.dto;

import lombok.*;
import metaint.replanet.rest.reviews.entity.Member;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrganizationDTO  {

    private Long orgCode;

    private String fileOriginName;

    private String fileSaveName;

    private String fileSavePath;

    private String fileExtension;

    private String orgDescription;

    private Member memberCode;

    private MemberDTO member;

}
