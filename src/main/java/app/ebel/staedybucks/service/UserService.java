package app.ebel.staedybucks.service;

import app.ebel.staedybucks.dto.UserDto;
import app.ebel.staedybucks.dto.UserStockDto;
import app.ebel.staedybucks.dto.request.AddInterestRqDto;
import app.ebel.staedybucks.dto.request.UserTransactionRqDto;
import app.ebel.staedybucks.dto.response.*;

import java.util.List;

public interface UserService {
    Long registerUser(UserDto userDto);

    Long deleteUser(Long userId);

    UserListRpDto getAllUserInfo();

    UserInfoRpDto getUserInfo(Long userId);

    UserClanInfoRpDto getUserRegisteredClan(Long userId);

    Long addUserInterest(AddInterestRqDto addInterestRqDto);

    Long followClanInterest(Long userId, Long interestId);

    UserInterestRpDto getUserInterest(Long userId);

    UserStockDto buyStock(UserTransactionRqDto transactionRqDto);
    UserStockDto sellStock(UserTransactionRqDto transactionRqDto);

    UserStockListRpDto getUserStockList(Long userId);

}
