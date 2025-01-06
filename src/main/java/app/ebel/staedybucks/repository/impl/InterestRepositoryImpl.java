package app.ebel.staedybucks.repository.impl;

import app.ebel.staedybucks.dto.InterestDto;
import app.ebel.staedybucks.dto.InterestFollowDto;
import app.ebel.staedybucks.dto.StockDto;
import app.ebel.staedybucks.dto.UserInterestSingleDto;
import app.ebel.staedybucks.dto.response.ClanInterestRpDto;
import app.ebel.staedybucks.dto.response.UserInterestRpDto;
import app.ebel.staedybucks.entity.Clan;
import app.ebel.staedybucks.entity.User;
import app.ebel.staedybucks.repository.custom.InterestRepositoryCustom;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.ebel.staedybucks.entity.QClan.clan;
import static app.ebel.staedybucks.entity.QInterest.interest;
import static app.ebel.staedybucks.entity.QInterestFollow.interestFollow;
import static app.ebel.staedybucks.entity.QStock.stock;
import static app.ebel.staedybucks.entity.QUser.user;
import static com.querydsl.core.types.Projections.*;

@Repository
@RequiredArgsConstructor
public class InterestRepositoryImpl implements InterestRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long createInterestFollow(Long userId, Long interestId) {

        return null;
    }

    @Override
    public UserInterestRpDto findByUserId(Long userId) {

        User target = queryFactory.selectFrom(user).where(user.id.eq(userId)).fetchOne();
        List<UserInterestSingleDto> userCreateInterestList = queryFactory.select(
                        Projections.fields(
                                UserInterestSingleDto.class,
                                interest.createdUser.id.as("creatorId"),
                                Projections.fields(
                                        StockDto.class,
                                        stock.code,
                                        stock.name,
                                        stock.type,
                                        stock.market
                                ).as("stockInfo"),
                                interest.capturedAt,
                                interest.closedAt,
                                interest.capturedPrice,
                                interest.targetPrice,
                                interest.type.as("tradingType")
                        ))
                .from(interest)
                .join(interest.stock, stock)
                .where(interest.createdUser.id.eq(userId))
                .fetch();

        List<UserInterestSingleDto> userFollowInterestList = queryFactory.select(
                        Projections.fields(
                                UserInterestSingleDto.class,
                                interest.createdUser.id.as("creatorId"),
                                constructor(
                                        StockDto.class,
                                        stock.code,
                                        stock.name,
                                        stock.type,
                                        stock.market
                                ).as("stockInfo"),
                                interest.capturedAt,
                                interest.closedAt,
                                interest.capturedPrice,
                                interest.targetPrice,
                                interest.type.as("tradingType"),
                                Projections.fields(
                                        InterestFollowDto.class,
                                        clan.id.as("clanId"),
                                        clan.name.as("clanName"),
                                        interestFollow.followedAt,
                                        interestFollow.closedAt
                                ).as("followInfo")
                        ))
                .from(interestFollow)
                .join(interestFollow.interest, interest)
                .join(interest.createdClan, clan)
                .where(interestFollow.user.id.eq(userId))
                .fetch();

        assert target != null;
        UserInterestRpDto result = UserInterestRpDto.builder()
                .userId(target.getId())
                .nickname(target.getNickname())
                .create(userCreateInterestList)
                .follow(userFollowInterestList)
                .build();

        return result;
    }

    @Override
    public ClanInterestRpDto findByClanId(Long clanId) {

        List<InterestDto> interestDtoList = queryFactory.select(
                        Projections.fields(
                                InterestDto.class,
                                interest.createdUser.id.as("creatorId"),
                                interest.createdUser.nickname.as("creatorName"),
                                interest.createdClan.id.as("clanId"),
                                interest.createdClan.name.as("clanName"),
                                Projections.fields(
                                        StockDto.class,
                                        stock.code,
                                        stock.name,
                                        stock.type,
                                        stock.market
                                ).as("stock"),
                                interest.capturedAt,
                                interest.closedAt,
                                interest.capturedPrice,
                                interest.targetPrice,
                                interest.type.as("tradingType")
                        )
                ).from(interest)
                .join(interest.stock, stock)
                .where(interest.createdClan.id.eq(clanId))
                .fetch();

        Clan target = queryFactory.selectFrom(clan).where(clan.id.eq(clanId)).fetchOne();
        ClanInterestRpDto dto = ClanInterestRpDto.builder()
                .clanId(target.getId())
                .clanName(target.getName())
                .interestList(interestDtoList)
                .build();

        return dto;
    }
}
