package app.ebel.staedybucks.dto;

import app.ebel.staedybucks.entity.UserClan;
import app.ebel.staedybucks.enums.UserRole;
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
