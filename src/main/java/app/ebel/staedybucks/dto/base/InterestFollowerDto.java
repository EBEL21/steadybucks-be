package app.ebel.staedybucks.dto.base;

import app.ebel.staedybucks.UserSimpleInfoDto;
import lombok.*;

@Data
public class InterestFollowerDto {
    UserSimpleInfoDto user;
    InterestFollowDto follow;
}
