package com.qiaolu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportStudentData {
    private List<String> clazzList;
    private List<Long> dataList;
}
