package app.ebel.steadybucks.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginTokenDto {

    private String grantType;
    private String accessToken;
}
