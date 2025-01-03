package app.ebel.staedybucks.repository.impl;

import app.ebel.staedybucks.repository.custom.StockRepositoryCustom;
import app.ebel.staedybucks.repository.custom.UserClanRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepositoryCustom {

    private final JPAQueryFactory queryFactory;
}
