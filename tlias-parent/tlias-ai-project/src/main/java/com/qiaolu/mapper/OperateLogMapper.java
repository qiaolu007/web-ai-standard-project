package com.qiaolu.mapper;

import com.github.pagehelper.Page;
import com.qiaolu.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

    // 使用注解,让每项数据的主键id自动返回
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from operate_log")
    Page<OperateLog> findAllOfPage();
}
