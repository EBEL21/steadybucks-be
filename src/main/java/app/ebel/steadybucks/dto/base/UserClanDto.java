package app.ebel.steadybucks.dto.base;

import app.ebel.steadybucks.entity.UserClan;
import app.ebel.steadybucks.enums.UserRole;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserClanDto {

    Long clanId;
    String clanName;
    LocalDate joinedAt;
    UserRole role;

    public UserClanDto(UserClan userClan) {
        this.clanId = userClan.getClan().getId();
        this.clanName = userClan.getClan().getName();
        this.joinedAt = userClan.getJoinedAt();
        this.role = userClan.getRole();
    }
}
