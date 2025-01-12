package app.ebel.steadybucks.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionResponse {
    private final String type;
    private final String errorCode;
    private final String message;
}
