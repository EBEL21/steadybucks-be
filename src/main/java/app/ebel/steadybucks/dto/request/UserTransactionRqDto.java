package app.ebel.steadybucks.dto.request;

import app.ebel.steadybucks.entity.Stock;
import app.ebel.steadybucks.entity.Transaction;
import app.ebel.steadybucks.entity.User;
import app.ebel.steadybucks.enums.TransactionType;
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

    public Transaction toEntity(User user, Stock stock) {
        System.out.println(user);
        System.out.println(stock);

        return Transaction.builder()
                .type(this.type)
                .pricePerUnit(this.pricePerUnit)
                .quantity(this.quantity)
                .time(this.time)
                .user(user)
                .stock(stock)
                .build();
    }
}
