package app.ebel.staedybucks.repository.custom;

import app.ebel.staedybucks.dto.request.UserRegisterClanRqDto;
import app.ebel.staedybucks.entity.UserClan;

public interface UserClanRepositoryCustom {

    UserClan findUserClanByIds(Long clanId, Long userId);

}
