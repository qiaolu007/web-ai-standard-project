package com.qiaolu.controller;

import com.qiaolu.pojo.ReportEmpJobData;
import com.qiaolu.pojo.ReportStudentData;
import com.qiaolu.pojo.Result;
import com.qiaolu.service.ClazzService;
import com.qiaolu.service.EmpService;
import com.qiaolu.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private EmpService empService;
    @Autowired
    private ClazzService clazzServicel;
    @Autowired
    private StudentService studentService;

    /**
     * 员工职位人数统计
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("员工职位人数统计~");
        ReportEmpJobData reportEmpJobData = empService.getEmpJobData();
        return Result.success(reportEmpJobData);
    }

    /**
     *员工性别统计
     * @return
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("员工性别统计~");
        List<Map<String, Object>> list = empService.getEmpGenderData();
        return Result.success(list);
    }

    /**
     * 班级人数统计
     */
    @GetMapping("/studentCountData")
    public Result studentCountData(){
        log.info("班级人数统计~");
        ReportStudentData reportStudentData = clazzServicel.studentCountData();
        return Result.success(reportStudentData);
    }

    /**
     *学员学历统计
     */
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        log.info("学员学历统计~");
        List<Map<String, Object>> list = studentService.studentDegreeData();
        return Result.success(list);
    }
}
