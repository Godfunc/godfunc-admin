package com.godfunc.modules.log.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.godfunc.modules.log.annotation.LogRecord;
import com.godfunc.modules.log.entity.LogOperation;
import com.godfunc.modules.log.enums.LogStatusEnum;
import com.godfunc.modules.log.service.LogOperationService;
import com.godfunc.modules.security.util.SecurityUser;
import com.godfunc.modules.sys.model.UserDetail;
import com.godfunc.util.HttpContextUtils;
import com.godfunc.util.IpUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面类
 *
 * @author godfunc
 * @email godfunc@outlook.com
 */
@RequiredArgsConstructor
@Aspect
@Component
public class LogOperationAspect {
    private final LogOperationService logOperationService;
    private final ObjectMapper objectMapper;

    @Pointcut("@annotation(com.godfunc.modules.log.annotation.LogRecord)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            //执行方法
            Object result = point.proceed();

            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveLog(point, time, LogStatusEnum.SUCCESS.getValue());

            return result;
        } catch (Exception e) {
            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveLog(point, time, LogStatusEnum.FAIL.getValue());

            throw e;
        }
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, Integer status) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<?> clazz = joinPoint.getTarget().getClass();
        Method method = clazz.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        LogRecord classAnnotation = clazz.getAnnotation(LogRecord.class);
        LogRecord annotation = method.getAnnotation(LogRecord.class);

        LogOperation log = new LogOperation();
        if (annotation != null) {
            //注解上的描述
            if (classAnnotation != null) {
                log.setOperation(classAnnotation.value() + "-" + annotation.value());
            } else {
                log.setOperation(annotation.value());
            }
        }
        //登录用户信息
        UserDetail user = SecurityUser.getUser();
        log.setCreateUser(user.getUsername());

        log.setStatus(status);
        log.setRequestTime((int) time);

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        if (request != null) {
            log.setIp(IpUtils.getIpAddr(request));
            log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
            log.setRequestUrl(request.getRequestURI());
        }

        //请求参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = null;
            if (args.length == 1) {
                params = objectMapper.writeValueAsString(args[0]);
            } else if (args.length > 1) {
                params = objectMapper.writeValueAsString(args);
            }
            log.setRequestParams(params);
        } catch (Exception e) {

        }

        //保存到DB
        logOperationService.save(log);
    }
}