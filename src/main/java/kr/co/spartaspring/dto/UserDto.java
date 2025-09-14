package kr.co.spartaspring.dto;

import kr.co.spartaspring.entity.User;
import kr.co.spartaspring.entity.UserRole;
import lombok.Builder;
import lombok.Getter;

public class UserDto {
    @Builder
    public static class Request {
        private String username;
        private String password;
        private String email;
        @Getter
        private boolean admin = false;
        @Getter
        private String adminToken = "";

        public User toEntity() {
            UserRole role;
            if(admin)
                role = UserRole.ADMIN;
            else
                role = UserRole.USER;
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .build();
        }
    }
}
