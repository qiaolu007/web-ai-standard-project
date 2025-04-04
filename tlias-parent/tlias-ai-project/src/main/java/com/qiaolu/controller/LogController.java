package com.qiaolu.controller;

import com.qiaolu.pojo.OperateLog;
import com.qiaolu.pojo.PageResult;
import com.qiaolu.pojo.Result;
import com.qiaolu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 日志记录分页查询
     */
    @GetMapping("/log/page")
    public Result findAllOfPage(Integer page, Integer pageSize) {
        PageResult<OperateLog> pageResult = logService.findAllOfPage(page, pageSize);
        return Result.success(pageResult);
    }
}
