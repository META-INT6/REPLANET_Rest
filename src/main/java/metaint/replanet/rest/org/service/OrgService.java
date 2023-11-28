package metaint.replanet.rest.org.service;


import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.auth.entity.MemberRole;

//import metaint.replanet.rest.auth.entity.Member;
import metaint.replanet.rest.auth.repository.MemberRepository;
import metaint.replanet.rest.auth.util.SecurityUtil;

import metaint.replanet.rest.org.dto.OrgRequestDTO;
import metaint.replanet.rest.org.entity.Organization;
import metaint.replanet.rest.org.repository.OrgMemberRepository;
import metaint.replanet.rest.org.repository.OrgRepository;

import metaint.replanet.rest.pay.entity.Member;

import metaint.replanet.rest.privacy.dto.MemberDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class OrgService {


    private final OrgRepository orgRepository;

    private final OrgMemberRepository orgMemberRepository;

    private final MemberRepository memberRepository;


    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OrgService(OrgRepository orgRepository, OrgMemberRepository orgMemberRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, MemberRepository memberRepository){
        this.orgRepository = orgRepository;
        this.orgMemberRepository = orgMemberRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }


    // 기부처 등록
    @Transactional
    public void orgRegist(OrgRequestDTO orgRequestDTO) {
        System.out.println(orgRequestDTO);

        // 권한 등록

        Organization org = modelMapper.map(orgRequestDTO, Organization.class);
        System.out.println(org);

        //orgRepository.save(org);


        //Organization org = orgRequestDTO.toOrganization(passwordEncoder);
        //return orgRepository.save(org);



    }


    public List<Member> getOrgList() {
        log.info("[getOrgList()] =============================================");

        MemberRole memberRole = MemberRole.ROLE_ORG;

        List<Member> orgList = orgMemberRepository.findAllByMemberRole(memberRole);

        log.info("[getOrgList() orgList] : " + orgList);

        return orgList;
}

    public List<Map<String, Object>> selectOrgInformation(int memberCode) {
        System.out.println("service 왔습니다~ 재단 기존 정보 가져가세요~");

        List<Object[]> orgInfo = orgRepository.selectOrgInformation(memberCode);
        List<Map<String, Object>> orgInformation = new ArrayList<>();
        for(Object[] info : orgInfo){
            Map<String, Object> orgInformationMap = new HashMap<>();
            orgInformationMap.put("orgEmail", info[0]);
            orgInformationMap.put("orgName", info[1]);
            orgInformationMap.put("phone", info[2]);
            orgInformationMap.put("description", info[3]);
            orgInformation.add(orgInformationMap);
        }

        return orgInformation;
    }

    public int verifyPassword(int memberCode, String orgPwd) {
        System.out.println("service 왔습니다~ 비밀번호 맞는지 확인합시다~");

        Member member = memberRepository.findById(Long.valueOf(memberCode)).get();
        if(passwordEncoder.matches(orgPwd, member.getPassword())){
            return 1;
        } else {
            return 0;
        }
    }

    public void updateOrgInformation(MemberDTO memberDTO, String orgDescription) {
        System.out.println("service 왔습니다~ 재단 정보 수정합시다~");

        Member memberM = memberRepository.findById((long) memberDTO.getMemberCode()).get();
        metaint.replanet.rest.privacy.entity.Organization organizationM = orgRepository.findById(memberDTO.getMemberCode()).get();

        if(organizationM != null && memberM != null){
            organizationM = organizationM.orgDescription(orgDescription).builder();
            orgRepository.save(organizationM);
            System.out.println("org 확인 : " + organizationM);

        }
         return;

    }
}
