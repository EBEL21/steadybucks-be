package app.ebel.staedybucks.service;

import app.ebel.staedybucks.dto.UserDto;
import app.ebel.staedybucks.dto.request.AddInterestRqDto;
import app.ebel.staedybucks.dto.response.UserClanInfoRpDto;
import app.ebel.staedybucks.dto.response.UserInfoRpDto;
import app.ebel.staedybucks.dto.response.UserInterestRpDto;

import java.util.List;

public interface UserService {
    Long registerUser(UserDto userDto);

    Long deleteUser(Long userId);

    List<UserInfoRpDto> getAllUserInfo();

    UserInfoRpDto getUserInfo(Long userId);

    UserClanInfoRpDto getUserRegisteredClan(Long userId);

    Long addUserInterest(AddInterestRqDto addInterestRqDto);

    Long followClanInterest(Long userId, Long interestId);

    UserInterestRpDto getUserInterest(Long userId);

}
