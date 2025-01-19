package app.ebel.steadybucks.repository.custom;

import app.ebel.steadybucks.dto.base.UserClanDto;
import app.ebel.steadybucks.entity.UserClan;

import java.util.List;

public interface UserClanRepositoryCustom {

    UserClan findUserClanByIds(Long clanId, Long userId);

    List<UserClanDto> findUserClanByUserId(Long userId);

}
