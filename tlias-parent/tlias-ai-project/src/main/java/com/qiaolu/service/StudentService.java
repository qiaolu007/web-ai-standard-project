package com.qiaolu.service;

import com.qiaolu.pojo.PageResult;
import com.qiaolu.pojo.Student;
import com.qiaolu.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

public interface StudentService {
    /**
     * 学员条件分页查询
     * @param studentQueryParam
     * @return
     */
    PageResult pageQuery(StudentQueryParam studentQueryParam);

    /**
     * 新增学员
     * @param student
     * @return
     */
     void saveStudent(Student student);

    /**
     * 根据id查询学员
     * @param id
     * @return
     */
    Student getInfoById(Integer id);

    /**
     * 修改学生基本信息
     */
    void updateInfo(Student student);

    /**
     * 删除学生
     */
    void deleteStudent(List<Integer> ids);

    /**
     * 违纪处理
     */
    void discipline(Integer id, Integer score);

    /**
     *学员学历统计
     */
    List<Map<String, Object>> studentDegreeData();
}
