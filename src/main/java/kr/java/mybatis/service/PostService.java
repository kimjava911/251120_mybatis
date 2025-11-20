package kr.java.mybatis.service;

import kr.java.mybatis.model.dto.PostDTO;
import kr.java.mybatis.model.dto.PostUpdateDTO;
import kr.java.mybatis.model.dto.PostWriteDTO;

import java.util.List;

public interface PostService {
    void write(PostWriteDTO dto);
    PostDTO findById(Long id);
    List<PostDTO> findAll();
    void update(PostUpdateDTO dto);
    void delete(Long postId);
}
