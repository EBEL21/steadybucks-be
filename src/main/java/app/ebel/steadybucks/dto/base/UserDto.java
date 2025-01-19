package app.ebel.steadybucks.dto.base;

import app.ebel.steadybucks.entity.Account;
import app.ebel.steadybucks.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    public User toEntity() {
        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .account(new Account())
                .build();
    }

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .build();
    }
}
