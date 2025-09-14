package kr.co.spartaspring.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.spartaspring.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final JwtUtil jwtUtil;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("Authorization")){
                token = cookie.getValue();
                break;
            }
        }
        String subToken = jwtUtil.substringToken(token);
        String username = jwtUtil.getUserInformationFromToken(subToken)
                        .getSubject();
        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
