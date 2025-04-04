package com.qiaolu.controller;

import com.qiaolu.anno.Log;
import com.qiaolu.pojo.Clazz;
import com.qiaolu.pojo.ClazzQueryParam;
import com.qiaolu.pojo.PageResult;
import com.qiaolu.pojo.Result;
import com.qiaolu.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 该接口用于班级列表数据的条件分页查询
     */
    @GetMapping
    public Result pagingQuery(ClazzQueryParam clazzQueryParam) {
        log.info("name = {}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.pagingQuery(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加班级
     */
    @Log
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz) {
        log.info("班级信息:" + clazz);
        clazzService.addClazz(clazz);
        return Result.success();
    }

    /**
     * 删除{id}班级,路径参数需要加上@PathVariable
     *
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("删除id为:{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据ID查询班级
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 更改班级信息
     *
     * @param clazz
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级
     */
    @GetMapping("/list")
    public Result findAll() {
        log.info("查询所有班级~");
        List<Clazz> list = clazzService.findAll();
        return Result.success(list);
    }
}
