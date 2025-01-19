package app.ebel.steadybucks.service.base;

import app.ebel.steadybucks.dto.request.AddInterestRqDto;
import app.ebel.steadybucks.dto.response.ClanInterestFollowersRpDto;
import app.ebel.steadybucks.dto.response.ClanInterestRpDto;
import app.ebel.steadybucks.dto.response.ClanListRpDto;
import app.ebel.steadybucks.dto.response.ClanMembersInfoRpDto;

public interface ClanService {

    ClanListRpDto getAllClans();
    ClanMembersInfoRpDto getClanMemberInfo(Long clanId);
    Long addClanInterest(Long clanId, AddInterestRqDto addInterestRqDto, Long userId);

    ClanInterestRpDto getClanInterest(Long clanId);

    ClanInterestFollowersRpDto getClanInterestFollowers(Long clanId, Long interestId);
}
