package app.ebel.staedybucks.repository.impl;

import app.ebel.staedybucks.dto.ClanInfoDto;
import app.ebel.staedybucks.repository.custom.ClanRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.ebel.staedybucks.entity.QClan.clan;
import static app.ebel.staedybucks.entity.QUserClan.userClan;

@Repository
@RequiredArgsConstructor
public class ClanRepositoryImpl implements ClanRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ClanInfoDto> findAllClans() {

        List<ClanInfoDto> clanInfoDtos = queryFactory.select(
                        Projections.fields(
                                ClanInfoDto.class,
                                clan.id,
                                clan.name,
                                clan.createAt,
                                userClan.user.id.count().intValue().as("numOfMembers")
                        )
                ).from(clan)
                .leftJoin(userClan).on(clan.id.eq(userClan.clan.id))
                .groupBy(clan.id)
                .fetch();

        return clanInfoDtos;
    }
}
