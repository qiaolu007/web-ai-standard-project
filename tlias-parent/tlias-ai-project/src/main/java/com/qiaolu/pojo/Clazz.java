package com.qiaolu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    private Integer id; //ID
    private String name; //班级名称
    private String room; //班级教室

    @DateTimeFormat(pattern = "yyyy-MM")
    private LocalDate beginDate; //开课时间
    @DateTimeFormat(pattern = "yyyy-MM")
    private LocalDate endDate; //结课时间

    private Integer masterId; //班主任
    private Integer subject; //学科

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createTime; //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updateTime; //修改时间

    private String status; // 状态
    private String masterName; // 班主任名字

    /**
     * 当前时间课程状态
     * @param currentDate
     */
    public void calculateState(LocalDate currentDate) {
        if (currentDate.isBefore(beginDate)) {
            this.status = "未开课"; // 未开课
        } else if (currentDate.isAfter(endDate)) {
            this.status = "已结课"; // 已结课
        } else {
            this.status = "在读"; // 在读
        }
    }
}
