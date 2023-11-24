package metaint.replanet.rest.privacy.service;

import metaint.replanet.rest.privacy.dto.MemberDTO;
import metaint.replanet.rest.privacy.entity.Member;
import metaint.replanet.rest.privacy.repository.PrivacyRepository;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivacyService {

    private StringEncryptor stringEncryptor;

    private final PrivacyRepository privacyRepository;

    @Autowired
    public PrivacyService(PrivacyRepository privacyRepository, StringEncryptor stringEncryptor){
        this.privacyRepository = privacyRepository;
        this.stringEncryptor = stringEncryptor;
    }

    public String encryptResidentNum(String residentNumber){
        return stringEncryptor.encrypt(residentNumber);
    }

    public String descryptResidentNum(String encryptedResidentNumber){
        return stringEncryptor.decrypt(encryptedResidentNumber);
    }

    public int updateMemberPrivacy(MemberDTO memberDTO) {
        System.out.println("service로 오는 것 확인");
        Member updatePrivacy = privacyRepository.findById(memberDTO.getMemberCode()).get();
        System.out.println("updatePrivacy 확인 : " + updatePrivacy);

        if(updatePrivacy != null){
            updatePrivacy = updatePrivacy.privacyStatus(memberDTO.getPrivacyStatus())
                    .residentNum(encryptResidentNum(memberDTO.getResidentNum()))
                    .builder();
            privacyRepository.save(updatePrivacy);

            System.out.println("동의 여부 확인 : " + updatePrivacy.getPrivacyStatus());
            System.out.println("암호화된 주민번호 확인 : " + updatePrivacy.getResidentNum());
            System.out.println("복호화된 주민번호 확인 : " + descryptResidentNum(updatePrivacy.getResidentNum()));

            return 1;
        }
        return 0;
    }

    public int withdrawMemberPrivacy(MemberDTO memberDTO) {
        System.out.println("service로 오는 것 확인");
        Member withdrawPrivacy = privacyRepository.findById(memberDTO.getMemberCode()).get();
        System.out.println("updatePrivacy 확인 : " + withdrawPrivacy);

        if(withdrawPrivacy != null){
            withdrawPrivacy = withdrawPrivacy.privacyStatus(memberDTO.getPrivacyStatus())
                    .residentNum("0")
                    .builder();
            privacyRepository.save(withdrawPrivacy);

            System.out.println("동의 여부 확인 : " + withdrawPrivacy.getPrivacyStatus());
            System.out.println("주민번호 초기화 확인 : " + withdrawPrivacy.getResidentNum());

            return 1;
        }
        return 0;
    }
}
