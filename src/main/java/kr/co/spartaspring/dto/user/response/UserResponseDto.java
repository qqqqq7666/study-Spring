package kr.co.spartaspring.dto.user.response;

import kr.co.spartaspring.entity.UserRole;
import lombok.Builder;

@Builder
public record UserResponseDto(
        String username,
        String email,
        UserRole role
) {
}
