package com.qiaolu.controller;

import com.qiaolu.anno.Log;
import com.qiaolu.pojo.Emp;
import com.qiaolu.pojo.EmpQueryParam;
import com.qiaolu.pojo.PageResult;
import com.qiaolu.pojo.Result;
import com.qiaolu.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    // -----------------  分页查询--------------------------
//    /**
//     * 员工分页查询
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
//       log.info("page=" + page + " pageSize=" + pageSize);
//        PageResult<Emp> pageResult = empService.page(page, pageSize);
//       return Result.success(pageResult);
//    }

    // ------------------ 条件分页查询-----------------------------

    /**
     * 员工条件分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询参数为: " + empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增员工(基本信息与工作经历)
     */
    @Log
    @PostMapping
    public Result saveEmp(@RequestBody Emp emp) throws Exception {
        log.info("请求参数: {}", emp);
        empService.saveeEmp(emp);
        return Result.success();
    }

    /**
     * 查询所有员工
     */
    @GetMapping("/list")
    public Result findAll() {
        log.info("查询所有员工");
        List<Emp> list = empService.findAll();
        return Result.success(list);
    }

    /**
     * 批量删除员工
     */
    @Log
    @DeleteMapping()
    public Result deletByIds(@RequestParam List<Integer> ids) {
        log.info("批量删除员工:{}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     *根据id查询员工信息
     */
    @GetMapping("{id}")
    public Result getInfoById(@PathVariable Integer id) {
        log.info("查询员工:{}", id);
        Emp emp = empService.getInfoById(id);
        return Result.success(emp);
    }

    /**
     *更新员工信息
     */
    @Log
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp) {
        log.info("修改员工信息:{}", emp);
        empService.updateEmp(emp);
        return Result.success();
    }

}
