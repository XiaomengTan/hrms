package com.hrms.aspect;

import com.alibaba.fastjson.JSONObject;
import com.hrms.admin.mapper.LogMapper;
import com.hrms.domain.common.entity.Log;
import com.hrms.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Order(1)//控制切面类的优先级，数字越小优先级越高
@Slf4j
@Component
@Aspect//aop类声明注解
public class LogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogMapper logMapper;

    @Around("@annotation(com.hrms.annotation.Log)")//切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //用于记录用户的操作日志
        //获取用户id
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer operateUser;
        if (Objects.isNull(claims)) {
            operateUser = 0;
        } else {
            operateUser = (Integer) claims.get("id");
        }

        //将不含有jwt的增改操作的操作人设置为0进行记录，如注册和修改密码等
        operateUser = Objects.requireNonNullElse(operateUser, 0);
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作的类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //记录方法执行前的时间
        long begin = System.currentTimeMillis();
        //方法返回值，调用原始目标方法
        Object result = joinPoint.proceed();
        //记录方法执行结束的时间
        long end = System.currentTimeMillis();

        //转换为json格式
        String returnValue = result != null ? JSONObject.toJSONString(result) : null;

        //计算操作耗时
        Long costTime = end - begin;

        //记录操作日志
        Log systemLog = new Log(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        logMapper.insert(systemLog);
        log.info("AOP操作日志：{}", systemLog);
        return result;
    }
}
