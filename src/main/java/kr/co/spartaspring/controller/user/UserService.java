package kr.co.spartaspring.controller.user;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.spartaspring.dto.user.request.UserLoginDto;
import kr.co.spartaspring.dto.user.request.UserSignupDto;
import kr.co.spartaspring.dto.user.response.UserResponseDto;
import kr.co.spartaspring.entity.User;
import kr.co.spartaspring.entity.UserRole;
import kr.co.spartaspring.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public UserResponseDto signup(UserSignupDto signupDto) {
        UserRole role;
        if (signupDto.isAdmin())
            role = UserRole.ADMIN;
        else
            role = UserRole.USER;

        // Validation 코드...
        if (signupDto.isAdmin() && !ADMIN_TOKEN.equals(signupDto.adminToken())) {
            log.error("invalid admin password");
            throw new IllegalArgumentException("invalid admin password");
        }

        User user = User.builder()
                .username(signupDto.username())
                .password(passwordEncoder.encode(signupDto.password()))
                .email(signupDto.email())
                .role(role)
                .build();

        return userRepository.save(user).toResponse();
    }

    public void login(UserLoginDto loginDto, HttpServletResponse response) {
        String username = loginDto.username();
        User user = userRepository.findByUsername(username).orElseThrow(
                () ->  new IllegalArgumentException("회원 없음")
        );
        // validation 들어가야됨

        String token = jwtUtil.createToken(username, user.getRole());
        jwtUtil.addJwtToCookie(token, response);

    }

}
