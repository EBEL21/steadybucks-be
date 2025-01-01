package app.ebel.staedybucks.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateClanRqDto {

    Long userId;
    String clanName;
}
