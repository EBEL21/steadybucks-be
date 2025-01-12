package app.ebel.steadybucks.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterestFollowDto {
    LocalDate followedAt;
    LocalDate ClosedAt;
}
