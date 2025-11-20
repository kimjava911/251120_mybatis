package kr.java.mybatis.service;

import kr.java.mybatis.model.dto.SignupDTO;

public interface MemberService {
    boolean signup(SignupDTO dto);
}
