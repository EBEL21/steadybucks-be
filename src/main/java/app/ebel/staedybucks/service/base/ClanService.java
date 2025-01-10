package app.ebel.staedybucks.service.base;

import app.ebel.staedybucks.dto.request.AddInterestRqDto;
import app.ebel.staedybucks.dto.response.ClanInterestFollowersRpDto;
import app.ebel.staedybucks.dto.response.ClanInterestRpDto;
import app.ebel.staedybucks.dto.response.ClanListRpDto;
import app.ebel.staedybucks.dto.response.ClanMembersInfoRpDto;

public interface ClanService {

    ClanListRpDto getAllClans();
    ClanMembersInfoRpDto getClanMemberInfo(Long clanId);
    Long addClanInterest(AddInterestRqDto addInterestRqDto);

    ClanInterestRpDto getClanInterest(Long clanId);

    ClanInterestFollowersRpDto getClanInterestFollowers(Long clanId, Long interestId);
}
