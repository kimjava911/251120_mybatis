package kr.java.mybatis.service;

public interface PostService {
    void write(PostWriteDTO dto);
    PostDTO findById();
    List<PostDTO> findAll();
    void update(PostUpdateDTO dto);
    void delete(Long postId);
}
