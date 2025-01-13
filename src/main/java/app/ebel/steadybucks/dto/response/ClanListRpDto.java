package app.ebel.steadybucks.dto.response;

import app.ebel.steadybucks.dto.base.ClanInfoDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClanListRpDto {
    List<ClanInfoDto> clans;
    int numOfClans;
}
