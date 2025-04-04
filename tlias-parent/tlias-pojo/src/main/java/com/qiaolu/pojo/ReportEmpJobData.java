package com.qiaolu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportEmpJobData {
    private List<String> jobList;
    private List<Long> dataList;
}
