package app.ebel.steadybucks.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionResponse {
    private String type;
    private String errorCode;
    private String message;
}
