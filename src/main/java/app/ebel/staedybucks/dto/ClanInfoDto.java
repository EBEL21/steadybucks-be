package app.ebel.staedybucks.dto;

import app.ebel.staedybucks.UserSimpleInfoDto;
import app.ebel.staedybucks.entity.Clan;
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
