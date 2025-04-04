package com.qiaolu.mapper;

import com.github.pagehelper.Page;
import com.qiaolu.pojo.Student;
import com.qiaolu.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     * 学员条件分页查询
     * @param studentQueryParam
     * @return
     */
    Page<Student> pageQuery(StudentQueryParam studentQueryParam);

    /**
     * 新增学员
     * @param student
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
    void discipline(Integer id, Integer score, int count);

    /**
     * 学员学历统计
     * @return
     */
    List<Map<String, Object>> studentDegreeData();
}
