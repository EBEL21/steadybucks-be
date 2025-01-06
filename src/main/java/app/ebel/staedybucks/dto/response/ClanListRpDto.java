package app.ebel.staedybucks.dto.response;

import app.ebel.staedybucks.dto.ClanInfoDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClanListRpDto {
    List<ClanInfoDto> clans;
    int numOfClans;
}
