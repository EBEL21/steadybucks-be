package app.ebel.staedybucks.service.impl;

import app.ebel.staedybucks.dto.base.ClanInfoDto;
import app.ebel.staedybucks.dto.base.InterestFollowerDto;
import app.ebel.staedybucks.dto.request.AddInterestRqDto;
import app.ebel.staedybucks.dto.response.ClanInterestFollowersRpDto;
import app.ebel.staedybucks.dto.response.ClanInterestRpDto;
import app.ebel.staedybucks.dto.response.ClanListRpDto;
import app.ebel.staedybucks.dto.response.ClanMembersInfoRpDto;
import app.ebel.staedybucks.entity.Clan;
import app.ebel.staedybucks.entity.Interest;
import app.ebel.staedybucks.entity.Stock;
import app.ebel.staedybucks.entity.User;
import app.ebel.staedybucks.enums.TradingType;
import app.ebel.staedybucks.repository.base.ClanRepository;
import app.ebel.staedybucks.repository.base.InterestRepository;
import app.ebel.staedybucks.repository.base.StockRepository;
import app.ebel.staedybucks.service.base.ClanService;
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

        Clan clan = clanRepository.findById(clanId).orElseThrow(() -> new EntityNotFoundException("Clan not found with id: " + clanId));
        return new ClanMembersInfoRpDto(clan);
    }

    @Override
    public Long addClanInterest(AddInterestRqDto addInterestRqDto) {
        Long clanId = addInterestRqDto.getClanId();
        Long userId = addInterestRqDto.getCreatorId();
        String stockCode = addInterestRqDto.getStockCode();

        Clan clan = clanRepository.findById(clanId).orElseThrow(() -> new EntityNotFoundException("Clan not found with id: " + clanId));
        Stock stock = stockRepository.findByCode(stockCode).orElseThrow(() -> new EntityNotFoundException("Stock not found with code: " + stockCode));;
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
