package com.qiaolu.mapper;

import com.qiaolu.pojo.Clazz;
import com.qiaolu.pojo.ClazzQueryParam;
import com.qiaolu.pojo.ReportStudentData;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {

    /**
     * 该接口用于班级列表数据的条件分页查询
     */
    List<Clazz> pagingQuery(ClazzQueryParam clazzQueryParam);

    /**
     * 该接口用于添加班级信息
     */
    void addClazz(Clazz clazz);

    /**
     * 根据id删除班级
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据id查询班级
     */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from  clazz where id = #{id}")
    Clazz getById(Integer id);

    /**
     * 修改班级
     */
    void update(Clazz clazz);

    /**
     * 查询所有班级
     */
    List<Clazz> findAll();

    /**
     * 班级人数统计
     */
    @MapKey("name")
    List<Map<String, Object>> studentCountData();
}




