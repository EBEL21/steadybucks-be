package app.ebel.steadybucks.repository.custom;

import app.ebel.steadybucks.dto.base.InterestFollowerDto;
import app.ebel.steadybucks.dto.response.ClanInterestRpDto;
import app.ebel.steadybucks.dto.response.UserInterestRpDto;

import java.util.List;

public interface InterestRepositoryCustom {
    UserInterestRpDto findByUserId(Long userId);

    ClanInterestRpDto findByClanId(Long clanId);

    List<InterestFollowerDto> findInterestFollowers(Long clanId, Long interestId);
}
