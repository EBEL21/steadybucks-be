package app.ebel.staedybucks.dto.response;

import app.ebel.staedybucks.dto.InterestDto;
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
