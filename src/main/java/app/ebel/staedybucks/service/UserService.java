package app.ebel.staedybucks.service;

import app.ebel.staedybucks.dto.UserDto;
import app.ebel.staedybucks.dto.response.UserClanInfoRpDto;
import app.ebel.staedybucks.dto.response.UserInfoRpDto;

public interface UserService {
    Long registerUser(UserDto userDto);

    Long deleteUser(Long userId);

    UserInfoRpDto getUserInfo(Long userId);

    UserClanInfoRpDto getUserRegisteredClan(Long userId);
}
