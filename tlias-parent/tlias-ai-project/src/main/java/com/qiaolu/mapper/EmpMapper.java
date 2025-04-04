package com.qiaolu.mapper;

import com.qiaolu.pojo.Emp;
import com.qiaolu.pojo.EmpQueryParam;
import com.qiaolu.pojo.LoginInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    // -------- 原始方法-----------------------
//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
//    Long total();
//
//    @Select("select emp.*, dept.name as deptName from emp left join dept on emp.dept_id = dept.id ORDER BY emp.update_time limit #{beginNum}, #{pageSize} ")
//    List<Emp> page(Integer beginNum, Integer pageSize);

    // --------  pageHelper 插件   -------------------
    List<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工-基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void saveEmp(Emp emp);


    /**
     * 查询所有员工信息
     */
    @Select("select id, username, password, name, gender, job, salary, image, entry_date, dept_id, create_time, update_time from emp")
    List<Emp> findAll();

    /**
     * 根据id删除员工基本信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     *根据id查询员工基本信息
     */
    Emp getInfoById(Integer id);

    /**
     * 根据id更新员工基本信息
     * @param emp
     */
    void updateEmpById(Emp emp);

    /**
     * 员工职位人数查询
     * @return
     */
    @MapKey("job")
    List<Map<String, Object>> getEmpJobData();

    /**
     * 员工性别统计
     * @return
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 根据部门id查询员工数量
     */
    Integer getNumByDeptID(Integer deptId);

    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}

