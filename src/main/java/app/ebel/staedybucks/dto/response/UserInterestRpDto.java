package app.ebel.staedybucks.dto.response;

import app.ebel.staedybucks.dto.base.UserInterestSingleDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserInterestRpDto {

    Long userId;
    String nickname;

    List<UserInterestSingleDto> create;
    List<UserInterestSingleDto> follow;
}
