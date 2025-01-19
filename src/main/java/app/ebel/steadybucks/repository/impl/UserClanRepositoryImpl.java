package app.ebel.steadybucks.repository.impl;

import app.ebel.steadybucks.dto.base.UserClanDto;
import app.ebel.steadybucks.entity.UserClan;
import app.ebel.steadybucks.repository.custom.UserClanRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.ebel.steadybucks.entity.QClan.clan;
import static app.ebel.steadybucks.entity.QUser.user;
import static app.ebel.steadybucks.entity.QUserClan.userClan;

@Repository
@RequiredArgsConstructor
public class UserClanRepositoryImpl implements UserClanRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public UserClan findUserClanByIds(Long clanId, Long userId) {
        return queryFactory.selectFrom(userClan)
                .where(user.id.eq(userId).and(clan.id.eq(clanId)))
                .fetchOne();
    }

    @Override
    public List<UserClanDto> findUserClanByUserId(Long userId) {
        return queryFactory.select(Projections.fields(
                UserClanDto.class,
                clan.id.as("clanId"),
                clan.name.as("clanName"),
                userClan.joinedAt,
                userClan.role
                ))
                .from(userClan)
                .join(userClan.clan, clan)
                .where(userClan.user.id.eq(userId))
                .fetch();
    }
}
