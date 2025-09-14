package kr.co.spartaspring.controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.spartaspring.entity.UserRole;
import kr.co.spartaspring.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;

    @GetMapping("/creat-jwt")
    public String createJwt(HttpServletResponse response) {
        String token = jwtUtil.createToken("nada", UserRole.USER);

        jwtUtil.addJwtToCookie(token, response);
        return "createJwt : " + token;
    }

    @GetMapping("/jwt")
    public String getJwt(@CookieValue(JwtUtil.HEADER) String tokenValue) {
        String token = jwtUtil.substringToken(tokenValue);

        if(!jwtUtil.validateToken(token))
            throw new IllegalArgumentException("Token Error");

        Claims info = jwtUtil.getUserInformationFromToken(token);

        return "getJwt : " + info.getSubject() + " | " + info.get(JwtUtil.KEY);
    }
}
