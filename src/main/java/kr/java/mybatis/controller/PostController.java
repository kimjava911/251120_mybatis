package kr.java.mybatis.controller;

import jakarta.servlet.http.HttpSession;
import kr.java.mybatis.model.domain.Post;
import kr.java.mybatis.model.dto.PostDTO;
import kr.java.mybatis.model.dto.PostUpdateDTO;
import kr.java.mybatis.model.dto.PostWriteDTO;
import kr.java.mybatis.service.MemberService;
import kr.java.mybatis.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String detail(@PathVariable("postId") Long postId, Model model,
                         HttpSession session) {
//        model.addAttribute("post", postService.findById(postId));
        PostDTO p = postService.findById(postId);
        String username = (String) session.getAttribute("username");
        Long myId = memberService.findByUsername(username).getMemberId();
        // 작성자 member id와 접속한 세션을 가지고 있는 member_id가 같은지를 구분
        model.addAttribute("owner", p.memberId().equals(myId));
        model.addAttribute("post", p);
        return "post_detail";
    }

    @GetMapping("/{postId}/remove")
    public String remove(@PathVariable("postId") Long postId,
                         RedirectAttributes redirectAttributes,
                         HttpSession session) {
        String username = (String) session.getAttribute("username");
        Long myId = memberService.findByUsername(username).getMemberId();
        Long postOwnerId = postService.findById(postId).memberId();
        if (!postOwnerId.equals(myId)) {
            redirectAttributes.addFlashAttribute("msg", "삭제할 권한이 없습니다");
            return "redirect:/";
        }
        postService.delete(postId);
        redirectAttributes.addFlashAttribute("msg", "정상 삭제 되었습니다");
        return "redirect:/";
    }

    @GetMapping("/{postId}/edit")
    public String edit(@PathVariable("postId") Long postId, Model model,
                       RedirectAttributes redirectAttributes,
                       HttpSession session) {
        String username = (String) session.getAttribute("username");
        Long myId = memberService.findByUsername(username).getMemberId();
        Long postOwnerId = postService.findById(postId).memberId();
        if (!postOwnerId.equals(myId)) {
            redirectAttributes.addFlashAttribute("msg", "수정할 권한이 없습니다");
            return "redirect:/";
        }
        PostDTO p = postService.findById(postId);
        model.addAttribute("post", p);
        return "post_update";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable("postId") Long postId,
                       @ModelAttribute PostUpdateDTO dto,
                       RedirectAttributes redirectAttributes,
                       HttpSession session) {
        String username = (String) session.getAttribute("username");
        Long myId = memberService.findByUsername(username).getMemberId();
        Long postOwnerId = postService.findById(postId).memberId();
        if (!postOwnerId.equals(myId)) {
            redirectAttributes.addFlashAttribute("msg", "수정할 권한이 없습니다");
            return "redirect:/";
        }
        postService.update(dto);
        return "redirect:/post/" + postId;
    }


}
