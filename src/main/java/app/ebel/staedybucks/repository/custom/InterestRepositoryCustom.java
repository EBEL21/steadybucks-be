package app.ebel.staedybucks.repository.custom;

import app.ebel.staedybucks.dto.base.InterestFollowerDto;
import app.ebel.staedybucks.dto.response.ClanInterestRpDto;
import app.ebel.staedybucks.dto.response.UserInterestRpDto;

import java.util.List;

public interface InterestRepositoryCustom {
    UserInterestRpDto findByUserId(Long userId);

    ClanInterestRpDto findByClanId(Long clanId);

    List<InterestFollowerDto> findInterestFollowers(Long clanId, Long interestId);
}
