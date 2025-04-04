package com.qiaolu.mapper;

import com.qiaolu.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /**
     * 根据emp_id保存员工工作经历
     * @param list
     */
    void savaEmpExpr(List<EmpExpr> list);

    /**
     * 删除员工经历
     * @param empIds
     */
    void deleteByIds(List<Integer> empIds);
}
