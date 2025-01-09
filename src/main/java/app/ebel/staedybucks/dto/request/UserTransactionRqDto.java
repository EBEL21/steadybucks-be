package app.ebel.staedybucks.dto.request;

import app.ebel.staedybucks.entity.Stock;
import app.ebel.staedybucks.entity.Transaction;
import app.ebel.staedybucks.entity.User;
import app.ebel.staedybucks.entity.eid.UserStockId;
import app.ebel.staedybucks.enums.TransactionType;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserTransactionRqDto {

    Long userId;
    TransactionType type;
    String stockCode;
    BigDecimal pricePerUnit;
    Integer quantity;
    LocalDateTime time;

    public Transaction toEntity(UserStockId id, User user, Stock stock) {
        return Transaction.builder()
                .id(id)
                .type(this.type)
                .pricePerUnit(this.pricePerUnit)
                .quantity(this.quantity)
                .time(this.time)
                .user(user)
                .stock(stock)
                .build();
    }
}
