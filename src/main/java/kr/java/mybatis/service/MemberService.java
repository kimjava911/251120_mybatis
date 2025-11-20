package kr.java.mybatis.service;

import kr.java.mybatis.model.domain.Member;
import kr.java.mybatis.model.dto.LoginDTO;
import kr.java.mybatis.model.dto.SignupDTO;

public interface MemberService {
    boolean signup(SignupDTO dto);
    boolean login(LoginDTO dto);
    Member findByUsername(String username);
}
