package app.ebel.staedybucks.entity.eid;

import app.ebel.staedybucks.entity.Stock;
import app.ebel.staedybucks.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class UserStockId implements Serializable {

    Long userId;
    String stockCode;
}
