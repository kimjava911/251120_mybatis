package kr.java.mybatis.controller;

import jakarta.servlet.http.HttpSession;
import kr.java.mybatis.model.dto.PostWriteDTO;
import kr.java.mybatis.service.MemberService;
import kr.java.mybatis.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    public PostController(PostService postService, MemberService memberService) {
        this.postService = postService;
        this.memberService = memberService;
    }

    @GetMapping
    public String post() {
        return "post";
    }

    @PostMapping
    public String write(
            @ModelAttribute PostWriteDTO dto,
            HttpSession session
    ) {
        String username = (String) session.getAttribute("username");
        System.out.println("username = " + username);
        // 1) memberId를 세션에 넣는 방법
        // 2) memberService에서 호출해서 쓰는 방법
        // 3) postService에 MemberMapper를 써서 호출
        Long memberId = memberService.findByUsername(username).getMemberId();
        System.out.println("memberId = " + memberId);
        postService.write(new PostWriteDTO(
                dto.title(),
                dto.content(),
                memberId
        ));
        return "redirect:/";
    }

    @GetMapping("/{postId}")
    public String detail(@PathVariable("postId") Long postId, Model model) {
        model.addAttribute("post", postService.findById(postId));
        return "post_detail";
    }
}
