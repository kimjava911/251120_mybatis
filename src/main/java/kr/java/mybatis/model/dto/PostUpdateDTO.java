package kr.java.mybatis.model.dto;

// 이미 auto increment로 생성되었기 때문에 수정 시 특정하려면 PK
public record PostUpdateDTO(Long postId, String title, String content, Long memberId) { }
