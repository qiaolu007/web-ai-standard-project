package com.qiaolu.aspect;

import com.qiaolu.mapper.LoginLogMapper;
import com.qiaolu.mapper.OperateLogMapper;
import com.qiaolu.pojo.*;
import com.qiaolu.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Around("@annotation(com.qiaolu.anno.Log)")
    public Object operationLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 记录程序运行前的系统时间
        Long begin = System.currentTimeMillis();
        // 执行目标程序
        Object result = proceedingJoinPoint.proceed();
        // 程序运行后的系统时间
        Long end = System.currentTimeMillis();

        Long costTime = end - begin;

        // 设置log日志实体
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId()); // 暂时为1
        operateLog.setOperateTime(LocalDateTime.now()); //操作时间
        operateLog.setClassName(proceedingJoinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(proceedingJoinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(proceedingJoinPoint.getArgs()));
        operateLog.setReturnValue(result.toString());
        operateLog.setCostTime(costTime);

        log.info("记录操作日志:{}", operateLog);
        operateLogMapper.insert(operateLog);

        return result;
    }

    @Around("execution(* com.qiaolu.controller.*.login(..))")
    public Object loginLog(ProceedingJoinPoint psj) throws Throwable {
        Long beginTime = System.currentTimeMillis();
        Result result = (Result) psj.proceed();
        Long endTime = System.currentTimeMillis();

        short isSuccess;
        String jwt;
        if (result.getCode() == 0) {
            isSuccess = 0;
            jwt = null;
        } else {
            isSuccess = 1;
            LoginInfo loginInfo = (LoginInfo) result.getData();
            jwt = loginInfo.getToken();
        }

        Long costTime = endTime - beginTime;
        Emp emp = (Emp) psj.getArgs()[0];
        String username = emp.getUsername();
        String password = emp.getPassword();
        LocalDateTime loginTime = LocalDateTime.now();


        EmpLoginLog empLoginLog = new EmpLoginLog(null, username, password
        , loginTime, isSuccess, jwt, costTime);

        loginLogMapper.insertLoginLog(empLoginLog);
        log.info("记录登录日志~{} :", empLoginLog);
        return result;
    }

    // 示例方法，获取当前用户ID
    private int getCurrentUserId() {
        // 这里应该根据实际情况从认证信息中获取当前登录用户的ID
        return CurrentHolder.getCurrentId(); // 示例返回值
    }
}
