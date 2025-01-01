package app.ebel.staedybucks.service.impl;

import app.ebel.staedybucks.dto.ClanDto;
import app.ebel.staedybucks.dto.response.ClanMembersInfoRpDto;
import app.ebel.staedybucks.entity.Clan;
import app.ebel.staedybucks.repository.ClanRepository;
import app.ebel.staedybucks.service.ClanService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ClanServiceImpl implements ClanService {

    private final ClanRepository clanRepository;

    @Override
    public ClanMembersInfoRpDto getClanMemberInfo(Long clanId) {

        Clan clan = clanRepository.findById(clanId).orElseThrow(() -> new EntityNotFoundException("Clan not found with id: " + clanId));
        return new ClanMembersInfoRpDto(clan);
    }
}
