package app.ebel.steadybucks.service.base;

import app.ebel.steadybucks.dto.base.UserDto;
import app.ebel.steadybucks.dto.base.UserStockDto;
import app.ebel.steadybucks.dto.request.AddInterestRqDto;
import app.ebel.steadybucks.dto.request.UserTransactionRqDto;
import app.ebel.steadybucks.dto.response.*;

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

    TradeSummaryRpDto getUserTradeSummary(Long userId);

}
