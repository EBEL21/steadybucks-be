package app.ebel.staedybucks.entity.eid;

import app.ebel.staedybucks.entity.Stock;
import app.ebel.staedybucks.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserStockId implements Serializable {

    @NotBlank
    Long userId;
    @NotBlank
    String stockCode;
}
