package app.ebel.steadybucks.dto.response;
import app.ebel.steadybucks.dto.base.InterestFollowerDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ClanInterestFollowersRpDto {

    // 팔로워 기본정보
    List<InterestFollowerDto> followers;
    int numOfFollower;

    // TODO: account 정보와 엮어서 보유주식 정보 추적하기

}
