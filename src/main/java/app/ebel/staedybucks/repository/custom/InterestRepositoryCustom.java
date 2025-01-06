package app.ebel.staedybucks.repository.custom;

import app.ebel.staedybucks.dto.response.ClanInterestRpDto;
import app.ebel.staedybucks.dto.response.UserInterestRpDto;

public interface InterestRepositoryCustom {

    Long createInterestFollow(Long userId, Long interestId);
    UserInterestRpDto findByUserId(Long userId);

    ClanInterestRpDto findByClanId(Long clanId);
}
