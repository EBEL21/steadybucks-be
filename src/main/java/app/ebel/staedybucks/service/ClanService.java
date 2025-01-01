package app.ebel.staedybucks.service;

import app.ebel.staedybucks.dto.ClanDto;
import app.ebel.staedybucks.dto.response.ClanMembersInfoRpDto;

public interface ClanService {
    ClanMembersInfoRpDto getClanMemberInfo(Long clanId);
}
