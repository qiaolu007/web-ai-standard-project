package com.qiaolu.service.iml;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qiaolu.mapper.OperateLogMapper;
import com.qiaolu.pojo.OperateLog;
import com.qiaolu.pojo.PageResult;
import com.qiaolu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceIml implements LogService {

    @Autowired
    private OperateLogMapper  operateLogMapper;

    @Override
    public PageResult<OperateLog> findAllOfPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Page<OperateLog> pageList = operateLogMapper.findAllOfPage();
        return new PageResult<>(pageList.getTotal(), pageList.getResult());
    }
}
