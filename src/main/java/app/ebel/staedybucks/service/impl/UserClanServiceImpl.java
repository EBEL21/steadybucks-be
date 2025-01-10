package app.ebel.staedybucks.service.impl;

import app.ebel.staedybucks.dto.request.CreateClanRqDto;
import app.ebel.staedybucks.dto.request.UserRegisterClanRqDto;
import app.ebel.staedybucks.entity.Clan;
import app.ebel.staedybucks.entity.User;
import app.ebel.staedybucks.entity.UserClan;
import app.ebel.staedybucks.enums.UserRole;
import app.ebel.staedybucks.repository.base.ClanRepository;
import app.ebel.staedybucks.repository.base.UserClanRepository;
import app.ebel.staedybucks.repository.base.UserRepository;
import app.ebel.staedybucks.service.base.UserClanService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserClanServiceImpl implements UserClanService {

    private final UserRepository userRepository;
    private final ClanRepository clanRepository;
    private final UserClanRepository userClanRepository;

    @Override
    public Long userRegisterClan(UserRegisterClanRqDto dto) {
        Long userId = dto.getUserId();
        Long clanId = dto.getClanId();

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }

        if (!clanRepository.existsById(clanId)) {
            throw new IllegalArgumentException("Clan not found");
        }

        User userProxy = User.builder().id(userId).build();

        Clan clanProxy = Clan.builder().id(clanId).build();

        UserClan userClan = UserClan.builder().user(userProxy).clan(clanProxy).role(UserRole.MEMBER).joinedAt(LocalDate.now()).build();

        UserClan savedUc = userClanRepository.save(userClan);
        return savedUc.getId();
    }

    @Override
    public Long createClan(CreateClanRqDto dto) {
        Long userId = dto.getUserId();
        String clanName = dto.getClanName();

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }

        if (clanRepository.existsClanByName(clanName)) {
            throw new IllegalArgumentException("Already exists clan name");
        }

        Clan newClan = Clan.builder().name(clanName).createAt(LocalDate.now()).build();

        Clan savedClan = clanRepository.save(newClan);

        User userProxy = User.builder().id(userId).build();
        UserClan uc = UserClan.builder().user(userProxy).clan(savedClan).role(UserRole.CAPTAIN).joinedAt(LocalDate.now()).build();

        UserClan savedUc = userClanRepository.save(uc);

        return savedClan.getId();
    }

    @Override
    public boolean deleteClan(Long clanId, Long userId) {
        if (Objects.equals(verifyUserRole(clanId, userId), UserRole.CAPTAIN.toString())) {
            Clan clan = clanRepository.findById(clanId).orElseThrow(() -> new EntityNotFoundException("Clan not found with id: " + clanId));
            clanRepository.delete(clan);

        } else {
            throw new IllegalArgumentException("This is a delete request from an unauthorized user");
        }
        return true;
    }

    @Override
    public String verifyUserRole(Long clanId, Long userId) {
        UserClan uc = userClanRepository.findUserClanByIds(clanId, userId);
        if (Objects.isNull(uc)) {
            throw new IllegalArgumentException("There is no data about given user-clan pair");
        }

        return uc.getRole().toString();
    }
}
