package app.ebel.steadybucks.repository.impl;

import app.ebel.steadybucks.dto.UserSimpleInfoDto;
import app.ebel.steadybucks.dto.base.*;
import app.ebel.steadybucks.dto.response.ClanInterestRpDto;
import app.ebel.steadybucks.dto.response.UserInterestRpDto;
import app.ebel.steadybucks.entity.Clan;
import app.ebel.steadybucks.entity.User;
import app.ebel.steadybucks.repository.custom.InterestRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.ebel.steadybucks.entity.QClan.clan;
import static app.ebel.steadybucks.entity.QInterest.interest;
import static app.ebel.steadybucks.entity.QInterestFollow.interestFollow;
import static app.ebel.steadybucks.entity.QStock.stock;
import static app.ebel.steadybucks.entity.QUser.user;
import static com.querydsl.core.types.Projections.*;

@Repository
@RequiredArgsConstructor
public class InterestRepositoryImpl implements InterestRepositoryCustom {

    private final JPAQueryFactory queryFactory;

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

    @Override
    public List<InterestFollowerDto> findInterestFollowers(Long clanId, Long interestId) {

        List<InterestFollowerDto> followerDtos = queryFactory.select(
                        Projections.fields(
                                InterestFollowerDto.class,
                                Projections.fields(
                                        UserSimpleInfoDto.class,
                                        user.id,
                                        user.nickname
                                ).as("user"),
                                Projections.fields(
                                        InterestFollowDto.class,
                                        interestFollow.followedAt,
                                        interestFollow.closedAt
                                ).as("follow")
                        )
                ).from(interestFollow)
                .join(interestFollow.interest, interest)
                .join(interestFollow.user, user)
                .where(clanInterestCheck(clanId), interestIdCheck(interestId))
                .fetch();

        return followerDtos;
    }

    private BooleanExpression followerCheck(Long userId) {
        return interestFollow.user.id.eq(userId);
    }

    private BooleanExpression userInterestCheck(Long userId) {
        return interest.createdUser.id.eq(userId);
    }

    private BooleanExpression clanInterestCheck(Long clanId) {
        return interest.createdClan.id.eq(clanId);
    }

    private BooleanExpression interestIdCheck(Long interestId) {
        return interest.id.eq(interestId);
    }

}
