package com.qiaolu.service.iml;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qiaolu.mapper.StudentMapper;
import com.qiaolu.pojo.PageResult;
import com.qiaolu.pojo.Student;
import com.qiaolu.pojo.StudentQueryParam;
import com.qiaolu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
public class StudentServiceIml implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult pageQuery(StudentQueryParam studentQueryParam) {
        int page = studentQueryParam.getPage();
        int pageSize = studentQueryParam.getPageSize();

        PageHelper.startPage(page, pageSize);

        Page<Student> pageList = studentMapper.pageQuery(studentQueryParam);


        return new PageResult(pageList.getTotal(), pageList.getResult());
    }

    @Override
    public void saveStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.saveStudent(student);
    }

    @Override
    public Student getInfoById(Integer id) {
        Student student = studentMapper.getInfoById(id);
        return student;
    }

    @Override
    public void updateInfo(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateInfo(student);
    }

    @Override
    public void deleteStudent(List<Integer> ids) {
        studentMapper.deleteStudent(ids);
    }

    @Override
    public void discipline(Integer id, Integer score) {
        Student student = studentMapper.getInfoById(id);
        int count = student.getViolationCount();
        int newScore = student.getViolationScore() + score;
        count++;
        studentMapper.discipline(id, newScore, count);
    }

    @Override
    public List<Map<String, Object>> studentDegreeData() {
        return studentMapper.studentDegreeData();
    }
}
