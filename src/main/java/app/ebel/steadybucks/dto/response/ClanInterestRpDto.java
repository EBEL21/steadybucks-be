package app.ebel.steadybucks.dto.response;

import app.ebel.steadybucks.dto.base.InterestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ClanInterestRpDto {

    Long clanId;
    String clanName;
    List<InterestDto> interestList;
}
