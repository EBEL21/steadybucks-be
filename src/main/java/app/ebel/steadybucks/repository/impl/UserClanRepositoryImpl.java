package app.ebel.steadybucks.repository.impl;

import app.ebel.steadybucks.entity.UserClan;
import app.ebel.steadybucks.repository.custom.UserClanRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
