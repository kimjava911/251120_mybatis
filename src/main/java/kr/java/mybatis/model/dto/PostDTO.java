package kr.java.mybatis.model.dto;

public record PostDTO(Long postId, String title, String content, Long memberId, String createdAt) {}