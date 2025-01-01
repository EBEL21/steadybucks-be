package app.ebel.staedybucks.dto;

import app.ebel.staedybucks.entity.Clan;
import app.ebel.staedybucks.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class ClanDto {

    private String name;

    public Clan toEntity() {
        return Clan.builder()
                .name(this.name)
                .createAt(LocalDate.now())
                .build();
    }

//    public static ClanDto fromEntity(Clan clan) {
//        return UserDto.builder()
//                .loginId(user.getLoginId())
//                .password(user.getPassword())
//                .nickName(user.getNickname())
//                .build();
//    }
}
