package kr.co.spartaspring.dto.user.request;

public record UserSignupDto(
        String username,
        String password,
        String email,
        boolean isAdmin,
        String adminToken) {
}
