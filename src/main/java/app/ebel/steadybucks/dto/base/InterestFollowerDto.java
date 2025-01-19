package app.ebel.steadybucks.dto.base;

import app.ebel.steadybucks.dto.UserSimpleInfoDto;
import lombok.*;

@Data
public class InterestFollowerDto {
    UserSimpleInfoDto user;
    InterestFollowDto follow;
}
