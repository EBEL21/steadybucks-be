package app.ebel.steadybucks.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserListRpDto {
    List<UserInfoRpDto> users;
    int numOfUser;
}
