package com.qiaolu.service;

import com.qiaolu.pojo.OperateLog;
import com.qiaolu.pojo.PageResult;

public interface LogService {


    /**
     * 日志记录分页查询
     */
    PageResult<OperateLog> findAllOfPage(Integer page, Integer pageSize);
}
