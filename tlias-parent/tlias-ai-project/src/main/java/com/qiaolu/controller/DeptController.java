package com.qiaolu.controller;

import com.qiaolu.anno.Log;
import com.qiaolu.pojo.Dept;
import com.qiaolu.pojo.Result;
import com.qiaolu.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门
     * @return Result
     */
    @GetMapping("/depts")
    public Result findAll() {
        log.info("查询所有部门数据");
        List<Dept> list = deptService.findAll();
        return Result.success(list);
    }


//     简单参数接受方式一
//     使用HttpServerletRequest 进行参数接收
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest httpServletRequest) {
//        String str = httpServletRequest.getParameter("id");
//        int id = Integer.parseInt(str);
//        System.out.println("删除数据id=" + id);
//        return Result.success();
//    }


//    简单参数接收方式二:
//    使用@RequestParam注解进行参数绑定
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam("id", required = false) Integer deptId) {
//        System.out.println("删除数据id=" + deptId);
//        return Result.success();
//    }

//    简单参数接收方式三:
//    传递参数与形参名一样即可正常接收

    /**
     * 根据id删除部门
     * @param id 条件id
     */
    @Log
    @DeleteMapping("/depts")
    public Result delete(Integer id) throws Exception {
        log.info("删除部门:{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     * josn 数据的处理-通过实体对象来接收,保证json格式的键名与对象属性名保持一致,并添加@RequestBody注解
     * 使用场景,主要在post和put请求中
     */
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id) {
        System.out.println("查询id:" + id + "数据" );
        Dept dept = deptService.getInfoById(id);
        return Result.success(dept);
    }

    /**
     * 更改部门信息
     */
    @Log
    @PutMapping("depts")
    public Result updateInfo(@RequestBody Dept dept) {
        System.out.println("修改部门:" + dept);
        deptService.updateInfo(dept);
        return Result.success();
    }



}
