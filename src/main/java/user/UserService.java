package user;

import kr.co.spartaspring.dto.UserDto;
import kr.co.spartaspring.entity.User;
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
    public void signup(UserDto.Request userRequest) {
        User user = userRequest.toEntity();
        user.encodePassword(passwordEncoder);

        if(userRequest.isAdmin() && !ADMIN_TOKEN.equals(userRequest.getAdminToken())) {
            log.error("invalid admin password");
            throw new IllegalArgumentException("invalid admin password");
        }

        userRepository.save(user);
    }

}
