package app.ebel.staedybucks.dto.base;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClanInfoDto {

    Long id;
    String name;
    LocalDate createAt;
    int numOfMembers;
}
