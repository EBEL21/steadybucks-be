package app.ebel.steadybucks.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRegisterClanRqDto {

    private Long userId;
    private Long clanId;
}
