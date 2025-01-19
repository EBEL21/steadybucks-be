package app.ebel.steadybucks.dto.base;

import app.ebel.steadybucks.entity.UserClan;
import app.ebel.steadybucks.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
