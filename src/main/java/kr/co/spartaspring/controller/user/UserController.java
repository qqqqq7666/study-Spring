package kr.co.spartaspring.controller.user;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.spartaspring.dto.user.request.UserLoginDto;
import kr.co.spartaspring.dto.user.request.UserSignupDto;
import kr.co.spartaspring.dto.user.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> signup(UserSignupDto signupDto) {
        return ResponseEntity.created(URI.create("test_redirect"))
                .body(userService.signup(signupDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(UserLoginDto loginDto, HttpServletResponse response) {
        userService.login(loginDto, response);

        return ResponseEntity.ok()
                .location(URI.create("/"))
                .build();
    }
}
