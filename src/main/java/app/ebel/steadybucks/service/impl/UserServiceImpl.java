package app.ebel.steadybucks.service.impl;

import app.ebel.steadybucks.dto.base.*;
import app.ebel.steadybucks.dto.request.AddInterestRqDto;
import app.ebel.steadybucks.dto.request.LoginRqDto;
import app.ebel.steadybucks.dto.request.UserTransactionRqDto;
import app.ebel.steadybucks.dto.response.*;
import app.ebel.steadybucks.entity.*;
import app.ebel.steadybucks.entity.eid.UserStockId;
import app.ebel.steadybucks.enums.TradingType;
import app.ebel.steadybucks.exception.community.ResourceNotFoundException;
import app.ebel.steadybucks.exception.community.UnmatchedPasswordException;
import app.ebel.steadybucks.repository.base.*;
import app.ebel.steadybucks.service.base.UserService;
import app.ebel.steadybucks.util.JwtUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static app.ebel.steadybucks.constants.TaxConstants.KOREA_STOCK_TAX;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final InterestRepository interestRepository;
    private final InterestFollowRepository interestFollowRepository;
    private final UserStockRepository userStockRepository;
    private final TransactionRepository transactionRepository;
    private final ProfitRepository profitRepository;

    private final EntityManager entityManager;

    private final BCryptPasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger("UserLogger");

    @Override
    public Long registerUser(UserDto userDto) {
        User user = User.builder()
                .loginId(userDto.getLoginId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickName())
                .account(new Account())
                .build();
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public String loginUser(LoginRqDto loginRqDto) {

        String id = loginRqDto.getId();
        String password = loginRqDto.getPassword();

        User user = userRepository.findByLoginId(id).orElseThrow(() -> new ResourceNotFoundException("User@" + id));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return JwtUtil.generateToken(id);
        } else {
            throw new UnmatchedPasswordException();
        }
    }

    @Override
    public Long deleteUser(Long userId) {
        User user = userRepository.findByIdOrThrow(userId, "User");
        userRepository.delete(user);
        return user.getId();
    }

    @Override
    public UserListRpDto getAllUserInfo() {
        List<User> users = userRepository.findAll();
        return UserListRpDto.builder()
                .users(users.stream().map(UserInfoRpDto::new).toList())
                .numOfUser(users.size())
                .build();
    }

    @Override
    public UserInfoRpDto getUserInfo(Long userId) {
        User user = userRepository.findByIdOrThrow(userId, "User");
        return new UserInfoRpDto(user);
    }

    @Override
    public UserClanInfoRpDto getUserRegisteredClan(Long userId) {
        User user = userRepository.findByIdOrThrow(userId, "User");
        return new UserClanInfoRpDto(user);
    }


    // TODO 무결성검사, 중복검사, 관종 follow 중인 clan member 조회
    @Override
    public Long addUserInterest(AddInterestRqDto addInterestRqDto) {
        Long userId = addInterestRqDto.getCreatorId();
        String stockCode = addInterestRqDto.getStockCode();
        User user = userRepository.findByIdOrThrow(userId, "User");
        Stock stock = stockRepository.findByCodeOrThrow(stockCode);

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

        Interest targetInterest = interestRepository.findByIdOrThrow(interestId, "Interest");
        User targetUser = userRepository.findByIdOrThrow(userId, "User");

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

    @Override
    public UserStockDto buyStock(UserTransactionRqDto transactionRqDto) {
        Long userId = transactionRqDto.getUserId();
        String stockCode = transactionRqDto.getStockCode();

        User user = userRepository.findByIdOrThrow(userId, "User");
        Stock stock = stockRepository.findByCodeOrThrow(stockCode);

        UserStockId usid = new UserStockId(userId, stockCode);
        UserStock userStock = userStockRepository.findById(usid).orElse(
                UserStock.builder()
                        .id(usid)
                        .user(user)
                        .stock(stock)
                        .quantity(0)
                        .totalValue(BigDecimal.valueOf(0))
                        .pricePerUnit(BigDecimal.valueOf(0))
                        .build()
        );

        int updatedQuantity = transactionRqDto.getQuantity() + userStock.getQuantity();
        BigDecimal newTotalPrice = transactionRqDto.getPricePerUnit().multiply(BigDecimal.valueOf(transactionRqDto.getQuantity()))
                .add(userStock.getTotalValue());
        BigDecimal newAvgPrice = newTotalPrice.divide(BigDecimal.valueOf(updatedQuantity), RoundingMode.FLOOR);


        userStock.setQuantity(updatedQuantity);
        userStock.setTotalValue(newTotalPrice);
        userStock.setPricePerUnit(newAvgPrice);
        userStockRepository.save(userStock);

        System.out.println(user);
        System.out.println(stock);
        System.out.println("in constructor");
        Transaction transaction = transactionRqDto.toEntity(user, stock);
        transactionRepository.save(transaction);

        return new UserStockDto(userStock);
    }

    @Override
    public UserStockDto sellStock(UserTransactionRqDto transactionRqDto) {
        Long userId = transactionRqDto.getUserId();
        String stockCode = transactionRqDto.getStockCode();

        UserStockId usid = new UserStockId(userId, stockCode);
        UserStock userStock = userStockRepository.findByIdOrThrow(usid, "UserStock");

        User user = entityManager.getReference(User.class, userId);
        Stock stock = entityManager.getReference(Stock.class, stockCode);

        // Save Transaction
        Transaction transaction = transactionRqDto.toEntity(user, stock);
        transactionRepository.save(transaction);

        // Save Profit
        BigDecimal userPPU = userStock.getPricePerUnit();
        BigDecimal transactionPPU = transactionRqDto.getPricePerUnit();


        BigDecimal buyCost = userPPU.multiply(BigDecimal.valueOf(transactionRqDto.getQuantity()));
        BigDecimal sellCost = transactionPPU.multiply(BigDecimal.valueOf(transactionRqDto.getQuantity()));
        BigDecimal tax = sellCost.multiply(KOREA_STOCK_TAX);
        BigDecimal profitAmount = sellCost.subtract(buyCost).subtract(tax);
        BigDecimal profitRate = profitAmount.divide(sellCost, MathContext.DECIMAL128).multiply(BigDecimal.valueOf(100));

        Profit newProfit = Profit.builder()
                .transaction(transaction)
                .profitAmount(profitAmount)
                .profitRate(profitRate)
                .tax(tax)
                .build();
        profitRepository.save(newProfit);


        // 계좌 정보 업데이트
        int updatedQuantity = userStock.getQuantity() - transactionRqDto.getQuantity();
        BigDecimal newTotalPrice = userStock.getTotalValue().min(sellCost);
        if (updatedQuantity == 0) {
            userStockRepository.delete(userStock);
        } else {
            userStock.setQuantity(updatedQuantity);
            userStock.setTotalValue(newTotalPrice);
            userStockRepository.save(userStock);
        }

        return new UserStockDto(userStock);
    }

    @Override
    public UserStockListRpDto getUserStockList(Long userId) {

        List<UserStock> userStocks = userStockRepository.findById_UserId(userId);

        List<UserStockDto> usList = userStocks.stream().map(UserStockDto::new).toList();
        return new UserStockListRpDto(userId, usList);
    }

    @Override
    public TradeSummaryRpDto getUserTradeSummary(Long userId) {

        CumulativeProfitDto total = transactionRepository.getTotalCumulativeProfit(userId);
        List<TransactionDetailDto> transactions = transactionRepository.getUserTransactions(userId);
        List<CumulativeStockProfitDto> stocks = transactionRepository.getStockCumulativeProfit(userId);

        return TradeSummaryRpDto.builder()
                .total(total)
                .transactions(transactions)
                .stocks(stocks)
                .build();
    }
}
