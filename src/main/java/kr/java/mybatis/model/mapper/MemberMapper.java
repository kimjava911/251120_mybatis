package kr.java.mybatis.model.mapper;

import kr.java.mybatis.model.domain.Member;

// @Mapper <- 굳이 안해도... <- Spring Boot에서는 별도로 MyBatisConfig를 두지 않아서 직접 해줘야함
// @MapperScan <- 이걸로 스캔함
public interface MemberMapper {
    int insert(Member member); // 추가
    Member findByUsername(String username); // username -> 조회
}
