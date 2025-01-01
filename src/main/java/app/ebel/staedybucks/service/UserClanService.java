package app.ebel.staedybucks.service;

import app.ebel.staedybucks.dto.request.CreateClanRqDto;
import app.ebel.staedybucks.dto.request.UserRegisterClanRqDto;

public interface UserClanService {
    Long userRegisterClan(UserRegisterClanRqDto dto);
    Long createClan(CreateClanRqDto dto);

    boolean deleteClan(Long clanId, Long userId);

    String verifyUserRole(Long clanId, Long userId);
}
