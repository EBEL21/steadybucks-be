package app.ebel.staedybucks.dto;

import app.ebel.staedybucks.UserSimpleInfoDto;
import lombok.*;

import java.time.LocalDate;

@Data
public class InterestFollowerDto {
    UserSimpleInfoDto user;
    InterestFollowDto follow;
}
