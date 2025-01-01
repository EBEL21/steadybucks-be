package app.ebel.staedybucks.dto.response;

import app.ebel.staedybucks.entity.Clan;
import app.ebel.staedybucks.enums.UserRole;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ClanMembersInfoRpDto {

    Long ClanId;
    String clanName;
    List<UserInfoRpDto> members;
    List<String> roles;

    public ClanMembersInfoRpDto(Clan clan) {
        this.ClanId = clan.getId();
        this.clanName = clan.getName();

        members = new ArrayList<>();
        roles = new ArrayList<>();

        clan.getClanMembers().forEach(userClan -> {
            roles.add(userClan.getRole().toString());
            members.add(new UserInfoRpDto(userClan.getUser()));
        });
    }
}
