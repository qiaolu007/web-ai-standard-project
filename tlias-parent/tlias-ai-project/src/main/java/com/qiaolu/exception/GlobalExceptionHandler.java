package com.qiaolu.exception;

import com.qiaolu.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // @RestControllerAdvice = @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result exceptionHandler(Exception e) {//方法形参中指定能够处理的异常类型
        log.error("程序出错了", e); // 打印堆栈中的异常信息
        return Result.error("对不起,操作失败,请联系管理员");
    }

    @ExceptionHandler
    public Result handlerDup(DuplicateKeyException e) {//方法形参中指定能够处理的异常类型
        log.error("程序某个地方重复了", e); // 打印堆栈中的异常信息
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在");
    }

    @ExceptionHandler
    public Result handlerBusEx(BusinessException e) {//方法形参中指定能够处理的异常类型
        log.error("程序出错了", e); // 打印堆栈中的异常信息
        return Result.error("对不起，当前部门下有员工，不能直接删除！");
    }
}
