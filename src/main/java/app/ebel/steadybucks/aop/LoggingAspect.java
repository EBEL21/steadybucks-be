package app.ebel.steadybucks.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut("execution(* app.ebel.steadybucks.controller..*(..))")
    public void controllerAPIs() {}

    @Around("controllerAPIs()")
    public Object logApiCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // API 요청 정보
        String method = request.getMethod(); // GET, POST 등
        String url = request.getRequestURI();
        String queryParams = request.getQueryString();
        String ip = request.getRemoteAddr();
        String sessionId = request.getRequestedSessionId();
        String user = request.getRemoteUser() != null ? request.getRemoteUser() : "Anonymous";

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

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
            logger.error("[USER: {}, SESSION: {}, METHOD: {}, URL: {}, FROM: {} IP: {}, EX: {}, MSG: {}, Execution Time: {}ms]",
                    user, sessionId, method, url, className + '.' + methodName, ip, e.getClass().getSimpleName(), e.getMessage(), (endTime - startTime));

            throw e;
        }

        long endTime = System.currentTimeMillis();
        logger.info("[USER: {}, SESSION: {}, METHOD: {}, URL: {}, IP: {}, StatusCode: {}, Execution Time: {}ms]",
                user, sessionId, method, url, ip, statusCode, (endTime - startTime));

        return result;
    }
}
