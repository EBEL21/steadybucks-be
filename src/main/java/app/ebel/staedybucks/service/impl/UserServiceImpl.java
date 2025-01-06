package app.ebel.staedybucks.service.impl;

import app.ebel.staedybucks.dto.UserDto;
import app.ebel.staedybucks.dto.request.AddInterestRqDto;
import app.ebel.staedybucks.dto.response.UserClanInfoRpDto;
import app.ebel.staedybucks.dto.response.UserInfoRpDto;
import app.ebel.staedybucks.dto.response.UserInterestRpDto;
import app.ebel.staedybucks.entity.Interest;
import app.ebel.staedybucks.entity.InterestFollow;
import app.ebel.staedybucks.entity.Stock;
import app.ebel.staedybucks.entity.User;
import app.ebel.staedybucks.enums.CreatorType;
import app.ebel.staedybucks.enums.TradingType;
import app.ebel.staedybucks.repository.InterestFollowRepository;
import app.ebel.staedybucks.repository.InterestRepository;
import app.ebel.staedybucks.repository.StockRepository;
import app.ebel.staedybucks.repository.UserRepository;
import app.ebel.staedybucks.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final InterestRepository interestRepository;
    private final InterestFollowRepository interestFollowRepository;

    @Override
    public Long registerUser(UserDto userDto) {
        User user = userDto.toEntity();
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public Long deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);
        return user.getId();
    }

    @Override
    public List<UserInfoRpDto> getAllUserInfo() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserInfoRpDto::new).toList();
    }

    @Override
    public UserInfoRpDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        return new UserInfoRpDto(user);
    }

    @Override
    public UserClanInfoRpDto getUserRegisteredClan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        return new UserClanInfoRpDto(user);
    }


    // TODO 무결성검사, 중복검사, 관종 follow 중인 clan member 조회
    @Override
    public Long addUserInterest(AddInterestRqDto addInterestRqDto) {
        Long userId = addInterestRqDto.getCreatorId();
        String stockCode = addInterestRqDto.getStockCode();
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Stock stock = stockRepository.findByCode(stockCode).orElseThrow(() -> new EntityNotFoundException("Stock not found with code: " + stockCode));;

        Interest newInterest = Interest.builder()
                .createdUser(user)
                .joinedAt(LocalDate.now())
                .capturedAt(addInterestRqDto.getCapturedAt())
                .closedAt(addInterestRqDto.getClosedAt())
                .stock(stock)
                .capturedPrice(addInterestRqDto.getCapturedPrice())
                .targetPrice(addInterestRqDto.getTargetPrice())
                .type(TradingType.valueOf(addInterestRqDto.getTradingType()))
                .build();

        Interest savedInterest = interestRepository.save(newInterest);
        return savedInterest.getId();
    }

    @Override
    public Long followClanInterest(Long userId, Long interestId) {

        Interest targetInterest = interestRepository.findById(interestId).orElseThrow(() -> new EntityNotFoundException("Interest not found with id: " + interestId));
        User targetUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));;

        InterestFollow newFollow = InterestFollow.builder()
                .user(targetUser)
                .interest(targetInterest)
                .followedAt(LocalDate.now())
                .closedAt(null)
                .build();

        InterestFollow saved = interestFollowRepository.save(newFollow);

        return saved.getId();
    }

    @Override
    public UserInterestRpDto getUserInterest(Long userId) {
        return interestRepository.findByUserId(userId);
    }
}
