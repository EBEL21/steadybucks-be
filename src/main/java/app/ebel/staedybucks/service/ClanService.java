package app.ebel.staedybucks.service;

import app.ebel.staedybucks.dto.ClanDto;
import app.ebel.staedybucks.dto.InterestDto;
import app.ebel.staedybucks.dto.request.AddInterestRqDto;
import app.ebel.staedybucks.dto.response.ClanInterestRpDto;
import app.ebel.staedybucks.dto.response.ClanMembersInfoRpDto;

import java.util.List;

public interface ClanService {
    ClanMembersInfoRpDto getClanMemberInfo(Long clanId);
    Long addClanInterest(AddInterestRqDto addInterestRqDto);

    ClanInterestRpDto getClanInterest(Long clanId);
}
