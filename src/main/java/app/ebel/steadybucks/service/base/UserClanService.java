package app.ebel.steadybucks.service.base;

import app.ebel.steadybucks.dto.request.CreateClanRqDto;
import app.ebel.steadybucks.dto.request.UserRegisterClanRqDto;

public interface UserClanService {
    Long userRegisterClan(UserRegisterClanRqDto dto);
    Long createClan(CreateClanRqDto dto);

    boolean deleteClan(Long clanId, Long userId);

    String verifyUserRole(Long clanId, Long userId);
}
