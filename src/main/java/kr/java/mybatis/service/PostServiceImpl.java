package kr.java.mybatis.service;

import kr.java.mybatis.model.domain.Post;
import kr.java.mybatis.model.dto.PostDTO;
import kr.java.mybatis.model.dto.PostUpdateDTO;
import kr.java.mybatis.model.dto.PostWriteDTO;
import kr.java.mybatis.model.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;

    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public void write(PostWriteDTO dto) {
        Post p = new Post();
        p.setTitle(dto.title());
        p.setContent(dto.content());
        p.setMemberId(dto.memberId());
        postMapper.insert(p);
    }

    @Override
    public PostDTO findById(Long id) {
        Post p = postMapper.findById(id);
        PostDTO dto = new PostDTO(
                p.getPostId(),
                p.getTitle(),
                p.getContent(),
                p.getMemberId(),
                p.getCreatedAt()
        );
        return dto;
    }

    @Override
    public List<PostDTO> findAll() {
        return postMapper.findAll()
                .stream().map(
            (p) -> new PostDTO(
                    p.getPostId(),
                    p.getTitle(),
                    p.getContent(),
                    p.getMemberId(),
                    p.getCreatedAt()
        )).toList();
    }

    @Override
    public void update(PostUpdateDTO dto) {
        Post p = postMapper.findById(dto.postId());
        p.setTitle(dto.title());
        p.setContent(dto.content());
        postMapper.update(p);
    }

    @Override
    public void delete(Long postId) {
        postMapper.delete(postId);
    }
}
