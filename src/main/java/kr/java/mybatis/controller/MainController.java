package kr.java.mybatis.controller;

import jakarta.servlet.http.HttpSession;
import kr.java.mybatis.model.dto.LoginDTO;
import kr.java.mybatis.model.dto.SignupDTO;
import kr.java.mybatis.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class MainController {
    private final MemberService memberService;

    public MainController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupDTO dto,
                         RedirectAttributes redirectAttributes) {
        if (memberService.signup(dto)) {
            return "redirect:/";
        } else {
            // 주소창으로 전달
            // redirectAttributes.addAttribute("msg", "가입 실패");
            // 바로 model로 전달
            redirectAttributes.addFlashAttribute("msg", "가입 실패");
            return "redirect:/signup";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO dto,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        if (memberService.login(dto)) {
            session.setAttribute("username", dto.username());
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("msg", "로그인 실패");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
