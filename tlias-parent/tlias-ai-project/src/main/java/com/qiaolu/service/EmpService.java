package com.qiaolu.service;

import com.qiaolu.pojo.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public interface EmpService {
    /**
     *分页条件查询 员工
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     *新增员工
     */
    void saveeEmp(Emp emp) throws Exception;

    /**
     * 查询所有员工
     */
    List<Emp> findAll();

    /**
     *根据id删除员工
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 更新员工信息
     * @param emp
     */
    void updateEmp(Emp emp);

    /**
     * 根据id查询员工数据
     * @param id
     * @return
     */
    Emp getInfoById(Integer id);

    /**
     * 员工职位人数统计
     *
     * @return
     */
    ReportEmpJobData getEmpJobData();

    /**
     * 员工性别统计
     * @return
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 登录
     * @param emp
     * @return
     */
    LoginInfo login(Emp emp);
}
