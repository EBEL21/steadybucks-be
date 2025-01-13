package app.ebel.steadybucks.entity.eid;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
