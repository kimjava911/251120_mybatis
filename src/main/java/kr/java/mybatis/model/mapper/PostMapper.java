package kr.java.mybatis.model.mapper;

import kr.java.mybatis.model.domain.Post;

import java.util.List;

// CRUD
public interface PostMapper {
    // 5개
    // C : insert
    // R : findById <- postId. (개별 상세 조회), findAll (전체 조회)
    // U : update
    // D : delete
    int insert(Post post);
    Post findById(Long postId); // username 필드 이번에는 PK.
    List<Post> findAll();
    int update(Post post);
    int delete(Long postId);
}
