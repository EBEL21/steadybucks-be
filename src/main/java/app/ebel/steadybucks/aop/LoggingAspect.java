package app.ebel.steadybucks.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final HttpServletRequest request;

    @Around("execution(* app.ebel.steadybucks.controller..*(..))")
    public Object logApiCall(ProceedingJoinPoint joinPoint) throws  Throwable {
        long startTime = System.currentTimeMillis();

        // API 요청 정보
        String method = request.getMethod(); // GET, POST 등
        String url = request.getRequestURI();
        String queryParams = request.getQueryString();
        String ip = request.getRemoteAddr();
        String sessionId = request.getRequestedSessionId();
        String user = request.getRemoteUser() != null ? request.getRemoteUser() : "Anonymous";

        logger.info("API Call Started: [Method: {}, URL: {}, Query: {}, IP: {}, Session: {}, User: {}]",
                method, url, queryParams, ip, sessionId, user);

        Object result;
        int statusCode = 200; // 기본 상태 코드
        try {
            result = joinPoint.proceed(); // 실제 메서드 실행

            // ResponseEntity에서 상태 코드 가져오기 (ResponseEntity일 경우만 처리)
            if (result instanceof ResponseEntity) {
                statusCode = ((ResponseEntity<?>) result).getStatusCode().value();
            }
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            logger.error("API Call Failed: [Method: {}, URL: {}, IP: {}, Error: {}, Execution Time: {} ms]",
                    method, url, ip, e.getMessage(), (endTime - startTime), e);

            throw e;
        }

        long endTime = System.currentTimeMillis();
        logger.info("API Call Completed: [Method: {}, URL: {}, StatusCode: {}, Execution Time: {} ms]",
                method, url, statusCode, (endTime - startTime));

        return result;
    }
}
