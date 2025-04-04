package com.qiaolu.service.iml;

import com.qiaolu.exception.BusinessException;
import com.qiaolu.mapper.DeptMapper;
import com.qiaolu.mapper.EmpMapper;
import com.qiaolu.pojo.Dept;
import com.qiaolu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceIml implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void deleteById(Integer id) throws Exception {
        if (empMapper.getNumByDeptID(id) == 0) {
            deptMapper.deleteById(id);
        } else {
            throw new BusinessException("无法删除部门，因为该部门下存在员工");
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getInfoById(Integer id) {
        return deptMapper.getInfoById(id);

    }

    @Override
    public void updateInfo(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateInfo(dept);
    }


}
