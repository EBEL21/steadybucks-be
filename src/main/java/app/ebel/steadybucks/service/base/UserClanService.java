package app.ebel.steadybucks.service.base;

import app.ebel.steadybucks.dto.request.CreateClanRqDto;
import app.ebel.steadybucks.dto.request.UserRegisterClanRqDto;

public interface UserClanService {
    Long userRegisterClan(Long clanId, Long userId);
    Long createClan(String clanName, Long userId);

    boolean deleteClan(Long clanId, Long userId);

    String verifyUserRole(Long clanId, Long userId);
}
