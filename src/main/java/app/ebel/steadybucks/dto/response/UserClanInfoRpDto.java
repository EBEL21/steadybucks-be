package app.ebel.steadybucks.dto.response;

import app.ebel.steadybucks.dto.base.UserClanDto;
import app.ebel.steadybucks.entity.User;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class UserClanInfoRpDto {
    Long userId;
    String userName;
    int numOfClans;
    List<UserClanDto> clans;


    public UserClanInfoRpDto(User user) {
        this.userId = user.getId();
        this.userName = user.getNickname();
        this.numOfClans = user.getRegisteredClan().size();
        if (numOfClans > 0) {
            this.clans = user.getRegisteredClan().stream().map(
                    UserClanDto::new
            ).toList();
        } else {
            this.clans = Collections.emptyList();
        }
    }
}
