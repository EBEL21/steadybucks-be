package app.ebel.steadybucks.dto.response;

import app.ebel.steadybucks.dto.base.UserInterestSingleDto;
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
