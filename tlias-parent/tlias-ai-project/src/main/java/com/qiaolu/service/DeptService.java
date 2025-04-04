package com.qiaolu.service;

import com.qiaolu.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     * @return
     */
    List<Dept> findAll();

    /**
     * 根据id删除部门
     * @param id
     */
    void deleteById(Integer id) throws Exception;

    /**
     * 根据id添加部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    Dept getInfoById(Integer id);

    /**
     * 更改部门信息
     * @param dept
     */
    void updateInfo(Dept dept);
}
