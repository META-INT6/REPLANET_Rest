package metaint.replanet.rest.privacy.service;

import metaint.replanet.rest.privacy.dto.MemberDTO;
import metaint.replanet.rest.privacy.entity.Member;
import metaint.replanet.rest.privacy.repository.PrivacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivacyService {

    private final PrivacyRepository privacyRepository;

    @Autowired
    public PrivacyService(PrivacyRepository privacyRepository){
        this.privacyRepository = privacyRepository;
    }

    public int updateMemberPrivacy(MemberDTO memberDTO) {
        System.out.println("service로 오는 것 확인");
        Member updatePrivacy = privacyRepository.findById(memberDTO.getMemberCode()).get();
        System.out.println("updatePrivacy 확인 : " + updatePrivacy);

        if(updatePrivacy != null){
            updatePrivacy = updatePrivacy.infoConsent(memberDTO.getInfoConsent())
                    .residentNum(memberDTO.getResidentNum())
                    .builder();
            privacyRepository.save(updatePrivacy);

            System.out.println("동의 여부 확인 : " + updatePrivacy.getInfoConsent());
            System.out.println("암호화된 주민번호 확인 : " + updatePrivacy.getResidentNum());

            return 1;
        }
        return 0;
    }
}
