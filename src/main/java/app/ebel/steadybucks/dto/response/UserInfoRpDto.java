package app.ebel.steadybucks.dto.response;

import app.ebel.steadybucks.entity.User;
import lombok.Getter;

@Getter
public class UserInfoRpDto {

    Long userId;
    String username;
    Long balance;

    public UserInfoRpDto(User user) {
        this.userId = user.getId();
        this.username = user.getNickname();
        this.balance = user.getAccount().getBalance();
    }
}
