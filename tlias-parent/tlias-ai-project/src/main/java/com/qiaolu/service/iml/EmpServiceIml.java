package com.qiaolu.service.iml;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qiaolu.mapper.EmpExprMapper;
import com.qiaolu.mapper.EmpLogMapper;
import com.qiaolu.mapper.EmpMapper;
import com.qiaolu.pojo.*;
import com.qiaolu.service.EmpService;
import com.qiaolu.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceIml implements EmpService {
    // ---------- 原始方法 -------------
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogMapper empLogMapper;
//    /**
//     * 员工分页查询
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        Long total = empMapper.total();
//        Integer beginNum = (page - 1) * pageSize;
//        List<Emp> list = empMapper.page(beginNum, pageSize);
//        return new PageResult<>(total, list);
//    }

    // --------------插件 pageHelper--------------------------

    /**
     * 员工条件分页查询
     *
     * @return
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        Page<Emp> pageList = (Page<Emp>) empMapper.page(empQueryParam);
        return new PageResult<>(pageList.getTotal(), pageList.getResult());
    }

    /**
     * 新增员工
     *
     * @param emp
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}) // 事务管理(默认是运行时异常会回滚,因此定义任何异常皆需要回滚,需要配置rollbackfor属性
    public void saveeEmp(Emp emp) throws Exception {
        try {
            // 1.补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            // 2. 保存员工基本信息
            empMapper.saveEmp(emp);

            // 3.保存员工的工作经历信息 --批量
            List<EmpExpr> list = emp.getExprList();
            if (!CollectionUtils.isEmpty(list)) {
                for (EmpExpr empExpr : list) {
                    empExpr.setEmpId(emp.getId());
                }
                empExprMapper.savaEmpExpr(list);
            }
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogMapper.insert(empLog);
        }
    }

    @Override
    public List<Emp> findAll() {
        List<Emp> list = empMapper.findAll();
        return list;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteByIds(ids);
        empExprMapper.deleteByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateEmp(Emp emp) {
        // 根据id修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmpById(emp);
        // 根据id删除员工工作经历信息
        Integer id = emp.getId();
        empExprMapper.deleteByIds(Arrays.asList(id));
        // 根据id添加员工工作经历信息
        List<EmpExpr> empExprs = emp.getExprList();
        if (!CollectionUtils.isEmpty(empExprs)) {
            empExprs.forEach(empExpr -> {
                empExpr.setEmpId(id);
                System.out.println("更改的emp_id:" + id);
                ;
            });
            empExprMapper.savaEmpExpr(emp.getExprList());
        }

    }

    @Override
    public Emp getInfoById(Integer id) {
        return empMapper.getInfoById(id);
    }

    @Override
    public ReportEmpJobData getEmpJobData() {
        List<Map<String, Object>> list = empMapper.getEmpJobData();
        ReportEmpJobData reportEmpJobData = new ReportEmpJobData();
        reportEmpJobData.setJobList((list.stream().map(dataMap -> (String) dataMap.get("job")).toList()));
        reportEmpJobData.setDataList(list.stream().map(dataMap -> (Long) dataMap.get("num")).toList());
        return reportEmpJobData;
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.getEmpGenderData();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empInfo = empMapper.getUsernameAndPassword(emp);
        Map<String, Object> map = new HashMap<>();

        if (empInfo != null) {
            map.put("id", empInfo.getId());
            map.put("name", empInfo.getName());
            String token = JwtUtil.generateToken(map);
            return new LoginInfo(empInfo.getId(), empInfo.getUsername(), empInfo.getName(), token);
        }
        return null;
    }
}
