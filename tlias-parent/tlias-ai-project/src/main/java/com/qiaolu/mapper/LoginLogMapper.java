package com.qiaolu.mapper;

import com.qiaolu.pojo.EmpLoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper {

    @Insert("insert into emp_login_log (username, password, login_time, is_success, jwt, cost_time) values(#{username}, #{password}, #{loginTime}, #{isSuccess}, #{jwt}, #{costTime}) ")
    void insertLoginLog(EmpLoginLog empLoginLog);
}
