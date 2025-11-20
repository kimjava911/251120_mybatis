package kr.java.mybatis.service;

import kr.java.mybatis.model.domain.Member;
import kr.java.mybatis.model.dto.SignupDTO;
import kr.java.mybatis.model.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public boolean signup(SignupDTO dto) {
        try {
            Member member = new Member();
            member.setUsername(dto.username());
            member.setPassword(dto.password());
            memberMapper.insert(member);
        } catch (Exception e) {
            // Unique로 인한 에러 혹은 너무 길어서...
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
