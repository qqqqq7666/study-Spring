package kr.co.spartaspring.entity;

import jakarta.persistence.*;
import kr.co.spartaspring.dto.user.response.UserResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Getter
    private UserRole role;

    @Builder
    public User(Long id, String username, String password, String email, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserResponseDto toResponse() {
        return UserResponseDto.builder()
                .username(username)
                .email(email)
                .role(role)
                .build();
    }
}
