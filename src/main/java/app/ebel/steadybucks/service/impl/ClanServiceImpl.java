package app.ebel.steadybucks.service.impl;

import app.ebel.steadybucks.dto.base.ClanInfoDto;
import app.ebel.steadybucks.dto.base.InterestFollowerDto;
import app.ebel.steadybucks.dto.request.AddInterestRqDto;
import app.ebel.steadybucks.dto.response.ClanInterestFollowersRpDto;
import app.ebel.steadybucks.dto.response.ClanInterestRpDto;
import app.ebel.steadybucks.dto.response.ClanListRpDto;
import app.ebel.steadybucks.dto.response.ClanMembersInfoRpDto;
import app.ebel.steadybucks.entity.Clan;
import app.ebel.steadybucks.entity.Interest;
import app.ebel.steadybucks.entity.Stock;
import app.ebel.steadybucks.entity.User;
import app.ebel.steadybucks.enums.TradingType;
import app.ebel.steadybucks.repository.base.ClanRepository;
import app.ebel.steadybucks.repository.base.InterestRepository;
import app.ebel.steadybucks.repository.base.StockRepository;
import app.ebel.steadybucks.service.base.ClanService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClanServiceImpl implements ClanService {

    private final ClanRepository clanRepository;
    private final StockRepository stockRepository;
    private final InterestRepository interestRepository;

    @Override
    public ClanListRpDto getAllClans() {
        List<ClanInfoDto> clans = clanRepository.findAllClans();
        return ClanListRpDto.builder()
                .clans(clans)
                .numOfClans(clans.size())
                .build();
    }

    @Override
    public ClanMembersInfoRpDto getClanMemberInfo(Long clanId) {

        Clan clan = clanRepository.findByIdOrThrow(clanId, "Clan");
        return new ClanMembersInfoRpDto(clan);
    }

    @Override
    public Long addClanInterest(AddInterestRqDto addInterestRqDto) {
        Long clanId = addInterestRqDto.getClanId();
        Long userId = addInterestRqDto.getCreatorId();
        String stockCode = addInterestRqDto.getStockCode();

        Clan clan = clanRepository.findByIdOrThrow(clanId, "Clan");
        Stock stock = stockRepository.findByCodeOrThrow(stockCode);
        User userProxy = User.builder().id(userId).build();

        Interest newInterest = Interest.builder()
                .createdUser(userProxy)
                .createdClan(clan)
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
    public ClanInterestRpDto getClanInterest(Long clanId) {

        return interestRepository.findByClanId(clanId);
    }

    @Override
    public ClanInterestFollowersRpDto getClanInterestFollowers(Long clanId, Long interestId) {

        List<InterestFollowerDto> followerDtos = interestRepository.findInterestFollowers(clanId, interestId);
        return ClanInterestFollowersRpDto.builder()
                .followers(followerDtos)
                .numOfFollower(followerDtos.size())
                .build();
    }
}
