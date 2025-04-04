package com.qiaolu.controller;

import com.qiaolu.anno.Log;
import com.qiaolu.pojo.PageResult;
import com.qiaolu.pojo.Result;
import com.qiaolu.pojo.Student;
import com.qiaolu.pojo.StudentQueryParam;
import com.qiaolu.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 学员条件分页查询
     *
     * @param studentQueryParam
     * @return
     */
    @GetMapping
    public Result pageQuery(StudentQueryParam studentQueryParam) {
        log.info("学员条件分页查询:{}", studentQueryParam);
        PageResult pageResult = studentService.pageQuery(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增学员
     *
     * @param student
     * @return
     */
    @Log
    @PostMapping
    public Result saveStudent(@RequestBody Student student) {
        log.info("新增学员:{}", student);
        studentService.saveStudent(student);
        return Result.success();
    }

    /**
     * 根据id查询学员
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id) {
        log.info("查询学员:{}",id);
        Student student = studentService.getInfoById(id);
        return Result.success(student);
    }

    /**
     * 修改学生基本信息
     */
    @Log
    @PutMapping
    public Result updateInfo(@RequestBody Student student) {
        log.info("更新学生信息:{}", student);
        studentService.updateInfo(student);
        return Result.success();
    }

    /**
     * 删除学生
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result deleteStudent(@PathVariable List<Integer> ids) {
        log.info("删除学生:{}", ids);
        studentService.deleteStudent(ids);
        return Result.success();
    }

    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result discipline(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪id:{}, 扣分:{}", id, score);
        studentService.discipline(id, score);
        return Result.success();
    }

}
