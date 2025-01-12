package app.ebel.steadybucks.repository.custom;

import app.ebel.steadybucks.entity.UserClan;

public interface UserClanRepositoryCustom {

    UserClan findUserClanByIds(Long clanId, Long userId);

}
